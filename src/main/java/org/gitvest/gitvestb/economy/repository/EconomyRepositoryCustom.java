package org.gitvest.gitvestb.economy.repository;

import org.gitvest.gitvestb.economy.repository.dto.MissionAttendance;

public interface EconomyRepositoryCustom {
  MissionAttendance getLastAccessDate(Long memberId);
}
