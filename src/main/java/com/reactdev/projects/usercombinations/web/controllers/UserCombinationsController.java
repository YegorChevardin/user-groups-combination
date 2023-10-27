package com.reactdev.projects.usercombinations.web.controllers;

import com.reactdev.projects.usercombinations.service.services.UserCombinationsService;
import com.reactdev.projects.usercombinations.web.dto.UserCombination;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for handling actions for getting users combinations
 *
 * @version 0.0.1
 */
@RestController
@RequestMapping("/api/v1/user-combinations")
@RequiredArgsConstructor
public class UserCombinationsController {
  private final UserCombinationsService userCombinationsService;

  /** Method for getting valid combinations */
  @GetMapping
  public ResponseEntity<List<UserCombination>> findAllCombinations() {
    List<UserCombination> result = userCombinationsService.generateNewCombinations();
    return ResponseEntity.ok(result);
  }
}
