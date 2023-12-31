package com.reactdev.projects.usercombinations.repository.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * User combination entity representation
 *
 * @version 0.0.1
 */
@Entity
@Table(name = "users_combinations")
@Getter
@Setter
@NoArgsConstructor
public class UserCombinationEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "first_user_id", referencedColumnName = "id")
  private UserEntity firstUser;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "second_user_id", referencedColumnName = "id")
  private UserEntity secondUser;
}
