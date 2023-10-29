package com.reactdev.projects.usercombinations.web.exceptions;

/**
 * Class for defining custom exceptions
 *
 * @version 0.0.1
 */
public abstract class CustomException extends RuntimeException {
  public CustomException(String message) {
    super(message);
  }

  public CustomException(String message, Exception cause) {
    super(message, cause);
  }
}
