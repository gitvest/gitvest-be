package org.gitvest.gitvestb.economy.repository;

import static org.gitvest.gitvestb.economy.entity.QMission.mission;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.gitvest.gitvestb.economy.repository.dto.MissionAttendance;

@RequiredArgsConstructor
public class EconomyRepositoryImpl implements EconomyRepositoryCustom {

  private final JPAQueryFactory queryFactory;

  @Override
  public MissionAttendance getLastAccessDate(Long memberId) {

    return queryFactory
        .select(Projections.constructor(
            MissionAttendance.class,
            mission.lastAccessDate
        ))
        .from(mission)
        .where(mission.member.memberId.eq(memberId))
        .fetchOne();
  }

  @Override
  public long setLastAccessDate(LocalDate cur, Long memberId) {
    return queryFactory
        .update(mission)
        .set(mission.lastAccessDate, cur)
        .where(mission.member.memberId.eq(memberId))
        .execute();
  }
}
