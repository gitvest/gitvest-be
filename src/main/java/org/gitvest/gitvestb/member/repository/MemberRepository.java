package org.gitvest.gitvestb.member.repository;

import org.gitvest.gitvestb.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom{

//  @Query(
//      "SELECT new org.gitvest.gitvestb.member.repository.dto.MemberProfile(m.profileImageUrl, m.nickname, a.balance)"
//          + " FROM Member m"
//          + " JOIN Account a ON m.memberId = a.member.memberId"
//          + " WHERE m.memberId = :memberId")
//  Optional<MemberProfile> findMemberProfile(@Param("memberId") Long memberId);

}
