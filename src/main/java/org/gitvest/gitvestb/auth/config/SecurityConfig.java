package org.gitvest.gitvestb.auth.config;

import lombok.RequiredArgsConstructor;
import org.gitvest.gitvestb.auth.filter.TokenAuthenticationFilter;
import org.gitvest.gitvestb.auth.handler.OAuth2SuccessHandler;
import org.gitvest.gitvestb.auth.service.CustomOAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
// @EnableMethodSecurity : 권한 부여관련 ROLE 추가시 활성화
public class SecurityConfig {

  private final CustomOAuth2UserService oAuth2UserService;
  private final OAuth2SuccessHandler oAuth2SuccessHandler;
  private final TokenAuthenticationFilter tokenAuthenticationFilter;

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return new WebSecurityCustomizer() {
      @Override
      public void customize(WebSecurity web) {
        web.ignoring().requestMatchers("/favicon.ico", "/error");
      }
    };
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http
        .csrf(AbstractHttpConfigurer::disable)
        .cors(AbstractHttpConfigurer::disable)
        .httpBasic(AbstractHttpConfigurer::disable)
        .formLogin(AbstractHttpConfigurer::disable)
        .logout(AbstractHttpConfigurer::disable)
        .sessionManagement(c ->
            c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        // request 인증 인가 설정
        .authorizeHttpRequests(request ->
            request.requestMatchers(new AntPathRequestMatcher("/")
                ,new AntPathRequestMatcher("/auth/success"))
                .permitAll()
                .anyRequest().authenticated())
        // oauth2 설정
        .oauth2Login(oauth ->
            oauth.userInfoEndpoint(c -> c.userService(oAuth2UserService))
                .successHandler(oAuth2SuccessHandler))
        // jwt 설정
        .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        // 인증 예외 핸들링

    return http.build();
  }
}
