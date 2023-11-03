package com.reactdev.projects.usercombinations.repository.repositories;

import com.reactdev.projects.usercombinations.repository.entities.MarkEntity;
import com.reactdev.projects.usercombinations.repository.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MarkRepository extends JpaRepository<MarkEntity, Long> {

    List<MarkEntity> findAllByDateAndUser(LocalDate date, UserEntity user);

}
