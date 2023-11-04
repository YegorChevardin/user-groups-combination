package com.reactdev.projects.usercombinations.repository.repositories;

import com.reactdev.projects.usercombinations.repository.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for users
 * @version 0.0.1
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findAllByTeamName(String teamName);
    List<UserEntity> findAllByIdIn(List<Long> ids);

}
