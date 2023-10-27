package com.reactdev.projects.usercombinations.service.services.impl;

import com.reactdev.projects.usercombinations.repository.entities.UserCombinationEntity;
import com.reactdev.projects.usercombinations.repository.entities.UserEntity;
import com.reactdev.projects.usercombinations.repository.repositories.UserCombinationsRepository;
import com.reactdev.projects.usercombinations.service.convertors.EntityDtoConvertor;
import com.reactdev.projects.usercombinations.service.services.UserCombinationsService;
import com.reactdev.projects.usercombinations.web.dto.User;
import com.reactdev.projects.usercombinations.web.dto.UserCombination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserCombinationsServiceImpl implements UserCombinationsService {
  private final UserCombinationsRepository userCombinationsRepository;
  private final EntityDtoConvertor<UserCombinationEntity, UserCombination>
      userCombinationEntityDtoConvertor;

  @Override
  public List<UserCombination> generateNewCombinations() {
    List<UserCombination> userCombination = userCombinationEntityDtoConvertor.convertEntityToDto(userCombinationsRepository.findAll());
    List<User> firstCommand = null;
    List<User> secondCommand = null;

    Collections.shuffle(firstCommand);
    Collections.shuffle(secondCommand);

    List<UserCombination> newCombinations;

    if (firstCommand.size() < secondCommand.size()) {
      newCombinations = performAction(firstCommand, secondCommand);
    } else {
      newCombinations = performAction(secondCommand, firstCommand);
    }

    //todo handle missing teammates
    //todo compare collections elements with previous

    return newCombinations;
  }

  private List<UserCombination> performAction(List<User> iterable, List<User> partners) {
    List<UserCombination> combinations = new ArrayList<>();

    for (User currentFirstUser : iterable) {
      UserCombination userCombination = new UserCombination();
      userCombination.setFirstUser(currentFirstUser);
      userCombination.setSecondUser(partners.get(iterable.indexOf(currentFirstUser)));
      combinations.add(userCombination);
    }

    return combinations;
  }
}
