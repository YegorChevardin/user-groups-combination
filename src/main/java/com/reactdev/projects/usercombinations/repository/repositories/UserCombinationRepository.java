package com.reactdev.projects.usercombinations.repository.repositories;

import com.reactdev.projects.usercombinations.repository.entities.UserCombinationEntity;
import com.reactdev.projects.usercombinations.repository.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCombinationRepository extends CrudRepository<UserCombinationEntity, Long> {


    List<UserCombinationEntity> findAll();

    void deleteAll();


}
