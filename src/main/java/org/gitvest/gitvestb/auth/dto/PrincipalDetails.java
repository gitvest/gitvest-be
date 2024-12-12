package org.gitvest.gitvestb.auth.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gitvest.gitvestb.member.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Slf4j
@AllArgsConstructor
public class PrincipalDetails implements OAuth2User {

  private Member member;
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

  // TODO : 권한 부여
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();

    grantedAuthorities.add(new GrantedAuthority() {
      @Override
      public String getAuthority() {
        return null;
      }
    });

    return null;
  }
}
