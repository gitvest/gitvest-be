package org.gitvest.gitvestb.global.exception.list;

import org.gitvest.gitvestb.global.exception.CustomException;
import org.gitvest.gitvestb.global.exception.ExceptionEnum;

public class DeniedException extends CustomException {

  public DeniedException(ExceptionEnum e) {
    super(e);
  }
}
