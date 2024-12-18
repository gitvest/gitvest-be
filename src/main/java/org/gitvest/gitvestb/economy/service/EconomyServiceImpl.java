package org.gitvest.gitvestb.economy.service;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.gitvest.gitvestb.economy.repository.EconomyRepository;
import org.gitvest.gitvestb.economy.repository.dto.MissionAttendance;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EconomyServiceImpl implements EconomyService {

  private final EconomyRepository economyRepository;

  @Override
  @Transactional
  public void checkAttendance(Long memberId) {
    // TODO : 에러 처리

    LocalDate cur = LocalDate.now();
    MissionAttendance dto = economyRepository.getLastAccessDate(memberId);

    if (!dto.lastAccessDate().equals(cur)) {
      long updatedRow = economyRepository.setLastAccessDate(cur, memberId);
    }
  }
}
