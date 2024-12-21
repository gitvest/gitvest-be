package org.gitvest.gitvestb.auth.dto;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Slf4j
@AllArgsConstructor
public class PrincipalDetails implements OAuth2User {

  private OAuth2UserInfo oAuth2UserInfo;
  private Map<String, Object> attributes;
  private String attributeKey;

  @Override
  public String getName() {
    return attributes.get(attributeKey).toString();
  }

  @Override
  public Map<String, Object> getAttributes() {
    return attributes;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singletonList(new SimpleGrantedAuthority("user"));
  }

  public String getEmail() {
    return oAuth2UserInfo.getEmail();
  }
}
