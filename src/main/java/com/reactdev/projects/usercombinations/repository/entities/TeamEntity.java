package com.reactdev.projects.usercombinations.repository.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity class that represents groups table
 *
 * @version 0.0.1
 */
@Entity
@Table(name = "teams")
@Getter
@Setter
@NoArgsConstructor
public class TeamEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", length = 100, nullable = false)
  private String name;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "team")
  private List<UserEntity> users = new ArrayList<>();
}
