package com.reactdev.projects.usercombinations.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User combination dto
 *
 * @version 0.0.1
 */
@Data
@NoArgsConstructor
public class UserCombination {
  private Long id;
  private User firstUser;
  private User secondUser;
}
