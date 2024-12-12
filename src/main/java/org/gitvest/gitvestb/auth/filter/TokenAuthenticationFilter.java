package org.gitvest.gitvestb.auth.filter;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gitvest.gitvestb.auth.provider.TokenProvider;
import org.gitvest.gitvestb.global.utils.StringUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

  private static final String TOKEN_KEY = "BEARER ";

  private final TokenProvider tokenProvider;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    log.info("doFilterInternal Start");
    String accessToken = resolveToken(request);

    if (tokenProvider.validateToken(accessToken)) {
      setAuthentication(accessToken);
    } else {
      // 만료되었을 경우 accessToken 재발급
      String reissueAccessToken = tokenProvider.reissueAccessToken(accessToken);

      if (StringUtils.hasText(reissueAccessToken)) {
        setAuthentication(reissueAccessToken);

        // 재발급된 accessToken 다시 전달
        response.setHeader(AUTHORIZATION, TOKEN_KEY + reissueAccessToken);
      }
    }

    filterChain.doFilter(request, response);
  }

  private String resolveToken(HttpServletRequest request) {
    String token = request.getHeader(AUTHORIZATION);

    if (StringUtil.isNull(token)) {
      return null;
    }

    return token.substring(TOKEN_KEY.length());
  }

  private void setAuthentication(String accessToken) {
    Authentication authentication = tokenProvider.getAuthentication(accessToken);
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }
}
