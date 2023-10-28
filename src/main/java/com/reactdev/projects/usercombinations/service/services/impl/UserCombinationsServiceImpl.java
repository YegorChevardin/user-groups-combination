package com.reactdev.projects.usercombinations.service.services.impl;

import com.reactdev.projects.usercombinations.repository.entities.UserCombinationEntity;
import com.reactdev.projects.usercombinations.repository.entities.UserEntity;
import com.reactdev.projects.usercombinations.repository.repositories.UserCombinationRepository;
import com.reactdev.projects.usercombinations.repository.repositories.UserRepository;
import com.reactdev.projects.usercombinations.service.convertors.EntityDtoConvertor;
import com.reactdev.projects.usercombinations.service.services.UserCombinationsService;
import com.reactdev.projects.usercombinations.web.dto.UserCombination;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserCombinationsServiceImpl implements UserCombinationsService {

  @Autowired
  private final EntityDtoConvertor<UserCombinationEntity, UserCombination>
      userCombinationEntityDtoConvertor;

  @Autowired
  private final UserCombinationRepository userCombinationRepository;

  @Autowired
  private final UserRepository userRepository;

  @Override
  public List<UserCombination> generateNewCombinations() {
    List<UserEntity> team1List = userRepository.findAllByTeam_Id(1L);
    List<UserEntity> team2List = userRepository.findAllByTeam_Id(2L);

    return generateCombinations(team1List, team2List);
  }


  public List<UserCombination> generateCombinations(List<UserEntity> command1, List<UserEntity> command2) {
    int minSize = Math.min(command1.size(), command2.size());
    List<UserCombination> combinations = new ArrayList<>();

    Collections.shuffle(command1);
    Collections.shuffle(command2);
    ifRepeated(combinations);

    for (int i = 0; i < minSize; i++) {
      UserCombinationEntity combinationEntity = new UserCombinationEntity();
      combinationEntity.setFirstUser(command1.get(i));
      combinationEntity.setSecondUser(command2.get(i));
      userCombinationRepository.save(combinationEntity);
      combinations.add(userCombinationEntityDtoConvertor.convertEntityToDto(combinationEntity));
    }



    return combinations;
  }

  public void ifRepeated(List<UserCombination> generatedCombinations){
    List<UserCombination> savedCombinations = new ArrayList<>();
    for(UserCombinationEntity entity : userCombinationRepository.findAll()){
      savedCombinations.add(userCombinationEntityDtoConvertor.convertEntityToDto(entity));
    }

    for (UserCombination generatedCombination: generatedCombinations){
      if (savedCombinations.contains(generatedCombination)){
        userCombinationRepository.deleteAll();
        return;
      }
    }
  }

}
