package com.reactdev.projects.usercombinations.repository.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Entity class that represents users from database
 *
 * @version 0.0.1
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", length = 45, nullable = false)
  String name;

  @Column(name = "second_name", length = 45, nullable = false)
  String secondName;

  @ElementCollection
  private List<Integer> marks;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "team_id", referencedColumnName = "id")
  private TeamEntity team;
}
