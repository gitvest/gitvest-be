package org.gitvest.gitvestb.global.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Getter
public class GlobalExceptionHandler {

  @ExceptionHandler({CustomException.class})
  public ResponseEntity<CustomExceptionEntity> exceptionHandler(HttpServletRequest request,
      final CustomException e) {
    return ResponseEntity.status(e.getError().getStatus())
        .body(CustomExceptionEntity.builder()
            .errorCode(e.getError().getErrorCode())
            .errorMessage(e.getError().getErrorMessage())
            .build());
  }
}
