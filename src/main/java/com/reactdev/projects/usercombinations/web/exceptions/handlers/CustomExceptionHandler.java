package com.reactdev.projects.usercombinations.web.exceptions.handlers;

import com.reactdev.projects.usercombinations.web.exceptions.CustomException;
import com.reactdev.projects.usercombinations.web.exceptions.impl.DataNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Custom exception handler
 *
 * @version 0.0.1
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler(DataNotFoundException.class)
  public ResponseEntity<Map<String, String>> handleCustomExceptions(CustomException exception) {
    Map<String, String> errorMap = new HashMap<>();

    errorMap.put("errorMessage", exception.getMessage());

    return ResponseEntity.badRequest().body(errorMap);
  }
}
