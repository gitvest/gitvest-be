package org.gitvest.gitvestb.member.repository;

import static org.gitvest.gitvestb.account.entity.QAccount.account;
import static org.gitvest.gitvestb.member.entity.QMember.member;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.gitvest.gitvestb.member.repository.dto.MemberProfile;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

  private final JPAQueryFactory query;

  @Override
  public Optional<MemberProfile> findMemberProfile(Long memberId) {
    return Optional.ofNullable(
        query.select(Projections.constructor(
                MemberProfile.class,
                member.profileImageUrl,
                member.nickname,
                account.balance))
            .from(member)
            .join(account).on(account.member.eq(member))
            .where(member.memberId.eq(memberId))
            .fetchOne()
    );
  }
}
