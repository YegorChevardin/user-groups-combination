package com.reactdev.projects.usercombinations.service.convertors.impl;

import com.reactdev.projects.usercombinations.repository.entities.TeamEntity;
import com.reactdev.projects.usercombinations.repository.entities.UserEntity;
import com.reactdev.projects.usercombinations.service.convertors.ReverseEntityDtoConvertor;
import com.reactdev.projects.usercombinations.web.dto.Team;
import com.reactdev.projects.usercombinations.web.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEntityDtoConvertor implements ReverseEntityDtoConvertor<UserEntity, User> {
  private final ReverseEntityDtoConvertor<TeamEntity, Team> teamEntityDtoConvertor;

  @Override
  public User convertEntityToDto(UserEntity entity) {
    User user = new User();

    user.setId(entity.getId());
    user.setName(entity.getName());
    user.setSecondName(entity.getSecondName());
    user.setTeam(teamEntityDtoConvertor.convertEntityToDto(entity.getTeam()));
    user.setMarks(entity.getMarks());

    return user;
  }

  @Override
  public UserEntity dtoToEntity(User dto) {
    UserEntity userEntity = new UserEntity();

    userEntity.setId(dto.getId());
    userEntity.setName(dto.getName());
    userEntity.setSecondName(dto.getSecondName());
    userEntity.setTeam(teamEntityDtoConvertor.dtoToEntity(dto.getTeam()));
    userEntity.setMarks(dto.getMarks());

    return userEntity;
  }
}
