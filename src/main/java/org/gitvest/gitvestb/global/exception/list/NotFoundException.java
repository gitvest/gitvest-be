package org.gitvest.gitvestb.global.exception.list;

import org.gitvest.gitvestb.global.exception.CustomException;
import org.gitvest.gitvestb.global.exception.ExceptionEnum;

public class NotFoundException extends CustomException {

  public NotFoundException(ExceptionEnum e) {
    super(e);
  }
}
