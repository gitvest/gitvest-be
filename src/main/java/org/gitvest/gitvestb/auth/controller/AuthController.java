package org.gitvest.gitvestb.auth.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.gitvest.gitvestb.auth.dto.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AuthController {

  @GetMapping("/auth/success")
  public ResponseEntity<LoginResponse> loginSuccess(@Valid LoginResponse loginResponse) {
    log.info("auth Success");
    return ResponseEntity.ok(loginResponse);
  }
}
