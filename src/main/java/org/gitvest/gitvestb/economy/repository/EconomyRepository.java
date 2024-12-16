package org.gitvest.gitvestb.economy.repository;

import org.gitvest.gitvestb.economy.entity.Mission;
import org.gitvest.gitvestb.economy.repository.dto.MissionAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EconomyRepository extends JpaRepository<Mission, Long>, EconomyRepositoryCustom {

  MissionAttendance getLastAccessDate(Long memberId);
}
