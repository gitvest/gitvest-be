package org.gitvest.gitvestb.global.exception.list;

import org.gitvest.gitvestb.global.exception.CustomException;
import org.gitvest.gitvestb.global.exception.ExceptionEnum;

public class AlreadyExistException extends CustomException {

  public AlreadyExistException(ExceptionEnum e) {
    super(e);
  }
}
