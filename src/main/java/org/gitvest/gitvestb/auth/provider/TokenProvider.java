package org.gitvest.gitvestb.auth.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.SIG;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gitvest.gitvestb.global.utils.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
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

    return Jwts.builder()
        .subject(authentication.getName())
        .claim("role", authorities)
        .issuedAt(now)
        .expiration(expireDate)
        .signWith(secretKey, SIG.HS512)
        .compact();
  }

  public Authentication getAuthentication(String token) {
    Claims claims = parseClaims(token);
    List<SimpleGrantedAuthority> authorities = getAuthorities(claims);

    // 2. security의 User 객체 생성
    User principal = new User(claims.getSubject(), "", authorities);
    return new UsernamePasswordAuthenticationToken(principal, token, authorities);
  }

  private List<SimpleGrantedAuthority> getAuthorities(Claims claims) {
    return Collections.singletonList(new SimpleGrantedAuthority(
        claims.get("role").toString()));
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
    } catch (MalformedJwtException e) {
      //throw new TokenException(INVALID_TOKEN);
    } catch (SecurityException e) {
      //throw new TokenException(INVALID_JWT_SIGNATURE);
    }
    return null;
  }

  public String reissueAccessToken(String accessToken) {
    if (StringUtils.hasText(accessToken)) {
//      Token token = tokenService.findByAccessTokenOrThrow(accessToken);
//      String refreshToken = token.getRefreshToken();
//
//      if (validateToken(refreshToken)) {
//        String reissueAccessToken = generateAccessToken(getAuthentication(refreshToken));
//        tokenService.updateToken(reissueAccessToken, token);
//        return reissueAccessToken;
//      }
    }
    return null;
  }
}
