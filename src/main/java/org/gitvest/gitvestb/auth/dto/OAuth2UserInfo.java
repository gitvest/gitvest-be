package org.gitvest.gitvestb.auth.dto;

import java.util.Map;
import java.util.Map.Entry;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.gitvest.gitvestb.global.utils.StringUtil;
import org.gitvest.gitvestb.member.entity.Member;

@Slf4j
@Builder
@Getter
public class OAuth2UserInfo {

  private Long socialId;
  private String nickname;
  private String profile;
  private String email;

  public static OAuth2UserInfo of(String registrationId, Map<String, Object> attributes) {
    if (registrationId.equals("github")) {
      return ofGithub(attributes);
    }
    return null;
  }

  private static OAuth2UserInfo ofGithub(Map<String, Object> attributes) {

    return OAuth2UserInfo.builder()
        .socialId(Long.parseLong(String.valueOf(attributes.get("id"))))
        .nickname((String)attributes.get("login"))
        .profile((String)attributes.get("avatar_url"))
        .email(nvl(attributes, "email", "html_url"))
        .build();
  }

  private static String nvl(Map<String, Object> attributes, String s1, String s2) {
    if (StringUtil.isNull((String)attributes.get(s1))) {
      return (String)attributes.get(s2);
    }
    return (String)attributes.get(s1);
  }

  public Member toEntity() {
    return Member.builder()
        .socialId(socialId)
        .profileImageUrl(profile)
        .nickname(nickname)
        .email(email)
        .build();
  }
}
