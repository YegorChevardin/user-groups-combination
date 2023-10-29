package com.reactdev.projects.usercombinations.service.services.impl;

import com.reactdev.projects.usercombinations.repository.entities.UserCombinationEntity;
import com.reactdev.projects.usercombinations.repository.entities.UserEntity;
import com.reactdev.projects.usercombinations.repository.repositories.UserCombinationRepository;
import com.reactdev.projects.usercombinations.repository.repositories.UserRepository;
import com.reactdev.projects.usercombinations.service.convertors.EntityDtoConvertor;
import com.reactdev.projects.usercombinations.service.services.UserCombinationsService;
import com.reactdev.projects.usercombinations.web.dto.UserCombination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserCombinationsServiceImpl implements UserCombinationsService {

  private final EntityDtoConvertor<UserCombinationEntity, UserCombination>
      userCombinationEntityDtoConvertor;
  private final UserCombinationRepository userCombinationRepository;
  private final UserRepository userRepository;

  @Autowired
  public UserCombinationsServiceImpl(EntityDtoConvertor<UserCombinationEntity, UserCombination> userCombinationEntityDtoConvertor, UserCombinationRepository userCombinationRepository, UserRepository userRepository) {
    this.userCombinationEntityDtoConvertor = userCombinationEntityDtoConvertor;
    this.userCombinationRepository = userCombinationRepository;
    this.userRepository = userRepository;
  }

  @Override
  public List<UserCombination> generateNewCombinations() {
    List<UserEntity> command1 = userRepository.findAllByTeam_Id(1L);
    List<UserEntity> command2 = userRepository.findAllByTeam_Id(2L);

    return generateCombinations(command1, command2);
  }


  public List<UserCombination> generateCombinations(List<UserEntity> command1, List<UserEntity> command2) {
    int minSize = Math.min(command1.size(), command2.size());
    List<UserCombination> combinations = new ArrayList<>();

    Collections.shuffle(command1);
    Collections.shuffle(command2);
    userCombinationRepository.deleteAll();

    for (int i = 0; i < minSize; i++) {
      UserCombinationEntity combinationEntity = new UserCombinationEntity();
      combinationEntity.setFirstUser(command1.get(i));
      combinationEntity.setSecondUser(command2.get(i));
      userCombinationRepository.save(combinationEntity);
      combinations.add(userCombinationEntityDtoConvertor.convertEntityToDto(combinationEntity));
    }

    return combinations;
  }


}
