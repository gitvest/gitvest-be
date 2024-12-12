package org.gitvest.gitvestb.auth.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gitvest.gitvestb.auth.provider.TokenProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

  private final TokenProvider tokenProvider;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
    log.info("onAuthenticationSuccess start");
    // AT, RT 발급
    String accessToken = tokenProvider.generateAccessToken(authentication);
    tokenProvider.generateRefreshToken(authentication, accessToken);

    // 토큰 전달을 위한 redirect
    String redirectUrl = UriComponentsBuilder.fromUriString("/auth/success")
        .queryParam("accessToken", accessToken)
        .build().toUriString();

    response.sendRedirect(redirectUrl);
    //response.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
    //response.setStatus(HttpStatus.OK.value());
  }
}
