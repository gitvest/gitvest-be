package org.gitvest.gitvestb.auth.entity;

import lombok.Getter;

@Getter
public enum TokenClaims {
  ROLE("ROLE"),
  ID("ID");

  private final String claim;

  TokenClaims(String claim) {
    this.claim = claim;
  }

  public String getName() {
    return claim;
  }
}
