package com.reactdev.projects.usercombinations.repository.repositories;

import com.reactdev.projects.usercombinations.repository.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for users
 * @version 0.0.1
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findAllByTeamName(String teamName);

    UserEntity findUserEntityById(Long id);

    @Query(value = "SELECT e.marks FROM user_entity_marks e JOIN users u ON u.id = e.user_entity_id WHERE u.id = :userId", nativeQuery = true)
    List<Integer> findMarksByUserId(@Param("userId") Long userId);
}
