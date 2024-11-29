package org.gitvest.gitvestb.global.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

  private ExceptionEnum error;

  public CustomException(ExceptionEnum e) {
    super(e.getErrorMessage());
    this.error = e;
  }
}
