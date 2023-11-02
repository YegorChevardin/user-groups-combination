package com.reactdev.projects.usercombinations.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Dto class for users
 *
 * @version 0.0.1
 */
@Data
@NoArgsConstructor
public class User {
  private Long id;
  private String name;
  private String secondName;
  private Team team;
  private List<Integer> marks;
  private boolean isPresent = true;

}
