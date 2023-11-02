package com.reactdev.projects.usercombinations.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * User combination dto
 *
 * @version 0.0.1
 */
@Getter
@Setter
@NoArgsConstructor
public class UserCombination {
  private Long id;
  private User firstUser;
  private User secondUser;
  private LocalDateTime timestamp;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserCombination that = (UserCombination) o;
    return Objects.equals(id, that.id) && Objects.equals(firstUser, that.firstUser) && Objects.equals(secondUser, that.secondUser) && Objects.equals(timestamp, that.timestamp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstUser, secondUser, timestamp);
  }
}
