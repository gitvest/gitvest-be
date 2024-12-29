package org.gitvest.gitvestb.economy.controller;

import lombok.RequiredArgsConstructor;
import org.gitvest.gitvestb.economy.service.EconomyService;
import org.gitvest.gitvestb.global.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/economy")
@RequiredArgsConstructor
public class EconomyController {

  private final EconomyService economyService;

  @GetMapping("/attendance")
  public ResponseEntity<ResponseDTO> checkAttendance(Long memberId) {
    // TODO: 추후 ID부분 AccessToken에서 가져오기

    economyService.checkAttendance(memberId);

    return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
        .message("출석 완료")
        .build());
  }

}
