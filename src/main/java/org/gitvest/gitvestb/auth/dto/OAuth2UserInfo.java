package org.gitvest.gitvestb.auth.dto;

import java.util.Map;
import java.util.Map.Entry;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.gitvest.gitvestb.member.entity.Member;

@Slf4j
@Builder
@Getter
public class OAuth2UserInfo {

  private Long socialId;
  private String name;
  private String profile;
  private String email;

  public static OAuth2UserInfo of(String registrationId, Map<String, Object> attributes) {
    if (registrationId.equals("github")) {
      return ofGithub(attributes);
    }
    return null;
  }

  private static OAuth2UserInfo ofGithub(Map<String, Object> attributes) {

//    for (Entry<String, Object> entrySet : attributes.entrySet()) {
//      log.info("-> entrySet.getKey() : {} , entrySet.getValue() : {}", entrySet.getKey(), entrySet.getValue());
//    }

    return OAuth2UserInfo.builder()
        .socialId(Long.parseLong(String.valueOf(attributes.get("id"))))
        .name((String)attributes.get("name"))
        .profile((String)attributes.get("avatar_url"))
        .email((String)attributes.get("blog"))
        .build();
  }

  public Member toEntity() {
    return Member.builder()
        .socialId(socialId)
        .profileImageUrl(profile)
        .nickname(name)
        .email(email)
        .build();
  }
}
