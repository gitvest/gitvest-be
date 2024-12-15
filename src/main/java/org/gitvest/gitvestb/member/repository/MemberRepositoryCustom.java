package org.gitvest.gitvestb.member.repository;

import java.util.Optional;
import org.gitvest.gitvestb.member.repository.dto.MemberProfile;

public interface MemberRepositoryCustom {

  Optional<MemberProfile> findMemberProfile(Long memberId);
}
