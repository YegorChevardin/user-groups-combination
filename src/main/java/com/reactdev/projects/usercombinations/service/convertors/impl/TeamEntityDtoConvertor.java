package com.reactdev.projects.usercombinations.service.convertors.impl;

import com.reactdev.projects.usercombinations.repository.entities.TeamEntity;
import com.reactdev.projects.usercombinations.service.convertors.EntityDtoConvertor;
import com.reactdev.projects.usercombinations.web.dto.Team;
import org.springframework.stereotype.Component;

@Component
public class TeamEntityDtoConvertor implements EntityDtoConvertor<TeamEntity, Team> {
  @Override
  public Team convertEntityToDto(TeamEntity entity) {
    Team group = new Team();

    group.setId(entity.getId());
    group.setName(entity.getName());

    return group;
  }
}
