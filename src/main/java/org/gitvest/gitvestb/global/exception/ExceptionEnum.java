package org.gitvest.gitvestb.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionEnum {

  NOT_VALID_ERROR(HttpStatus.BAD_REQUEST, "E001", "유효하지 않은 값입니다.");

  private final HttpStatus status;
  private final String errorCode;
  private String errorMessage;

  ExceptionEnum(HttpStatus status, String errorCode, String errorMessage) {
    this.status = status;
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }
}
