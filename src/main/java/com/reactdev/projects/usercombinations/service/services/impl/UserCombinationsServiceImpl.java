package com.reactdev.projects.usercombinations.service.services.impl;

import com.reactdev.projects.usercombinations.repository.entities.UserCombinationEntity;
import com.reactdev.projects.usercombinations.service.convertors.EntityDtoConvertor;
import com.reactdev.projects.usercombinations.service.services.UserCombinationsService;
import com.reactdev.projects.usercombinations.web.dto.UserCombination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserCombinationsServiceImpl implements UserCombinationsService {
  private final EntityDtoConvertor<UserCombinationEntity, UserCombination>
      userCombinationEntityDtoConvertor;

  @Override
  public List<UserCombination> generateNewCombinations() {
    // todo business logic
    return null;
  }
}
