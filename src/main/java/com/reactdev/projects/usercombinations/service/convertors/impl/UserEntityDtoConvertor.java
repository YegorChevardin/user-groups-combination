package com.reactdev.projects.usercombinations.service.convertors.impl;

import com.reactdev.projects.usercombinations.repository.entities.GroupEntity;
import com.reactdev.projects.usercombinations.repository.entities.UserEntity;
import com.reactdev.projects.usercombinations.service.convertors.EntityDtoConvertor;
import com.reactdev.projects.usercombinations.web.dto.Group;
import com.reactdev.projects.usercombinations.web.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEntityDtoConvertor implements EntityDtoConvertor<UserEntity, User> {
  private final EntityDtoConvertor<GroupEntity, Group> groupEntityDtoConvertor;

  @Override
  public User convertEntityToDto(UserEntity entity) {
    User user = new User();

    user.setId(entity.getId());
    user.setName(entity.getName());
    user.setSecondName(entity.getSecondName());
    user.setGroup(groupEntityDtoConvertor.convertEntityToDto(entity.getGroup()));

    return user;
  }
}
