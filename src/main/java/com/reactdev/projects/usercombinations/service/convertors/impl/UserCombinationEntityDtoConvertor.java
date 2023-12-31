package com.reactdev.projects.usercombinations.service.convertors.impl;

import com.reactdev.projects.usercombinations.repository.entities.UserCombinationEntity;
import com.reactdev.projects.usercombinations.repository.entities.UserEntity;
import com.reactdev.projects.usercombinations.service.convertors.ReverseEntityDtoConvertor;
import com.reactdev.projects.usercombinations.web.dto.User;
import com.reactdev.projects.usercombinations.web.dto.UserCombination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCombinationEntityDtoConvertor
    implements ReverseEntityDtoConvertor<UserCombinationEntity, UserCombination> {
  private final ReverseEntityDtoConvertor<UserEntity, User> userEntityDtoConvertor;

  @Override
  public UserCombination convertEntityToDto(UserCombinationEntity entity) {
    UserCombination userCombination = new UserCombination();

    userCombination.setId(entity.getId());
    userCombination.setFirstUser(userEntityDtoConvertor.convertEntityToDto(entity.getFirstUser()));
    userCombination.setSecondUser(
        userEntityDtoConvertor.convertEntityToDto(entity.getSecondUser()));

    return userCombination;
  }

  @Override
  public UserCombinationEntity dtoToEntity(UserCombination dto) {
    UserCombinationEntity userCombinationEntity = new UserCombinationEntity();

    userCombinationEntity.setId(dto.getId());
    userCombinationEntity.setFirstUser(userEntityDtoConvertor.dtoToEntity(dto.getFirstUser()));
    userCombinationEntity.setSecondUser(userEntityDtoConvertor.dtoToEntity(dto.getSecondUser()));

    return userCombinationEntity;
  }
}
