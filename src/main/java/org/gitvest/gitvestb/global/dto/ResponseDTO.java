package org.gitvest.gitvestb.global.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ResponseDTO<T> {

  private T data;
  private String message;

  public static <T> ResponseDTO<T> success(T data, ResponseMessage message) {
    return new ResponseDTO<>(data, message.getMessage());
  }

  public static <T> ResponseDTO<T> success(ResponseMessage message) {
    return new ResponseDTO<>(null, message.getMessage());
  }

}
