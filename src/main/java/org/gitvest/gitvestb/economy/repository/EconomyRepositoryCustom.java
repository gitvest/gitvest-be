package org.gitvest.gitvestb.economy.repository;

import java.time.LocalDate;
import org.gitvest.gitvestb.economy.repository.dto.MissionAttendance;

public interface EconomyRepositoryCustom {
  MissionAttendance getLastAccessDate(Long memberId);
  long setLastAccessDate(LocalDate cur, Long memberId);
}
