package org.gitvest.gitvestb.economy.repository;


import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.gitvest.gitvestb.economy.entity.QMission;
import org.gitvest.gitvestb.economy.repository.dto.MissionAttendance;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class EconomyRepositoryImpl implements EconomyRepositoryCustom {

  private final JPAQueryFactory queryFactory;

  @Override
  public MissionAttendance getLastAccessDate(Long memberId) {
    QMission mission = QMission.mission;

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
  @Transactional
  public long setLastAccessDate(LocalDate cur, Long memberId) {
    QMission mission = QMission.mission;
    return queryFactory
        .update(mission)
        .set(mission.lastAccessDate, cur)
        .where(mission.member.memberId.eq(memberId))
        .execute();
  }
}
