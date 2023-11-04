package com.reactdev.projects.usercombinations.service.services;

import com.reactdev.projects.usercombinations.web.dto.User;
import com.reactdev.projects.usercombinations.web.dto.UserCombination;

import java.util.List;

/**
 * Service for performing business logic with generation of users combinations
 *
 * @version 0.0.1
 */
public interface UserCombinationsService {
  /** Method for generating new combinations */
  List<UserCombination> generateNewCombinations(List<User> users);
}
