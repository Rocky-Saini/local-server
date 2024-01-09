package com.digital.signage.exceptions;

public class InvalidJwtException extends Exception {
  private boolean isExpired = false;

  public InvalidJwtException() {
  }

  public InvalidJwtException(Throwable t) {
    super(t);
  }

  public InvalidJwtException(Throwable t, boolean isExpired) {
    super(t);
    this.isExpired = isExpired;
  }

  public void setExpired(boolean isExpired) {
    this.isExpired = isExpired;
  }

  public boolean isExpired() {
    return this.isExpired;
  }
}