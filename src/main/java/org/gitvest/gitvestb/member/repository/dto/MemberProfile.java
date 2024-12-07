package org.gitvest.gitvestb.member.repository.dto;

public record MemberProfile(
    String profileImageUrl,
    String nickname,
    Long balance
) {
}
