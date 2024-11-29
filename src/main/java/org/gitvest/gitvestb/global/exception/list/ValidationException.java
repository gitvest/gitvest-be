package org.gitvest.gitvestb.global.exception.list;

import org.gitvest.gitvestb.global.exception.CustomException;
import org.gitvest.gitvestb.global.exception.ExceptionEnum;

public class ValidationException extends CustomException {

  public ValidationException(ExceptionEnum e) {
    super(e);
  }
}
