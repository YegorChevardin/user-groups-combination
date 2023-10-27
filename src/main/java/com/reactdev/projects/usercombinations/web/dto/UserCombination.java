package com.reactdev.projects.usercombinations.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof UserCombination that)) return false;

    if ((this.firstUser.equals(that.getFirstUser()) && this.secondUser.equals(that.getSecondUser()))
        || (this.firstUser.equals(that.getSecondUser())
            && this.secondUser.equals(that.getFirstUser()))) {
      return true;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(getFirstUser(), getSecondUser());
  }
}
