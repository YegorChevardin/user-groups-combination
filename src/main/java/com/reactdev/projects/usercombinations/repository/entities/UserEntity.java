package com.reactdev.projects.usercombinations.repository.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "group_id", referencedColumnName = "id")
  private GroupEntity group;
}
