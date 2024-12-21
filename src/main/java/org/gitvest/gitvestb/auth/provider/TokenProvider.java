package org.gitvest.gitvestb.auth.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.SIG;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gitvest.gitvestb.auth.dto.PrincipalDetails;
import org.gitvest.gitvestb.auth.entity.TokenClaims;
import org.gitvest.gitvestb.global.utils.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.StringUtils;

@Slf4j
@RequiredArgsConstructor
@Component
public class TokenProvider {

  @Value("${jwt.key}")
  private String key;
  private SecretKey secretKey;

  private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30L;
  private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60L * 24 * 7;

  @PostConstruct
  private void setSecretKey() {
    secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
  }

  public String generateAccessToken(Authentication authentication) {
    log.info("generateAccessToken start");
    return generateToken(authentication, ACCESS_TOKEN_EXPIRE_TIME);
  }

  public void generateRefreshToken(Authentication authentication, String accessToken) {
    log.info("generateRefreshToken start");
    String refreshToken = generateToken(authentication, REFRESH_TOKEN_EXPIRE_TIME);
    // TODO : in-memory 에 RT 저장
  }

  private String generateToken(Authentication authentication, long expireTime) {
    Date now = new Date();
    Date expireDate = new Date(now.getTime() + expireTime);

    String authorities = authentication.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining());

    Map<String, String> claims = new HashMap<>();
    claims.put(TokenClaims.ROLE.getName(), authorities);            // 사용자 권한
    claims.put(TokenClaims.ID.getName(), authentication.getName()); // 사용자 소셜 id

    return Jwts.builder()
        .subject(authentication.getName())
        .claims(claims)
        .issuedAt(now)
        .expiration(expireDate)
        .signWith(secretKey, SIG.HS512)
        .compact();
  }

  public Authentication getAuthentication(String token) {
    Claims claims = parseClaims(token);
    List<SimpleGrantedAuthority> authorities = getAuthorities(claims);

    Map<String, Object> attributes = new HashMap<>();
    attributes.put("id", claims.get(TokenClaims.ID.getName()));
    PrincipalDetails principalDetails = new PrincipalDetails(attributes, "id");

    return new UsernamePasswordAuthenticationToken(principalDetails, token, authorities);
  }

  private List<SimpleGrantedAuthority> getAuthorities(Claims claims) {
    return Collections.singletonList(new SimpleGrantedAuthority(
        claims.get(TokenClaims.ROLE.getName()).toString()));
  }

  public boolean validateToken(String token) {
    if (StringUtil.isNull(token)) {
      return false;
    }

    Claims claims = parseClaims(token);
    return claims.getExpiration().after(new Date());
  }

  private Claims parseClaims(String token) {
    try {
      return Jwts.parser().verifyWith(secretKey).build()
          .parseSignedClaims(token).getPayload();
    } catch (ExpiredJwtException e) {
      return e.getClaims();
    } catch (SecurityException e) {

    }
    return null;
  }

  public String reissueAccessToken(String accessToken) {
    if (StringUtils.hasText(accessToken)) {
      // TODO : RT 재발급
    }
    return null;
  }
}
