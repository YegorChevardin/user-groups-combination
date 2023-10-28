package com.reactdev.projects.usercombinations.repository.repositories;

import com.reactdev.projects.usercombinations.repository.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    List<UserEntity> findAll();

    List<UserEntity> findAllByTeam_Id(Long team_id);
}
