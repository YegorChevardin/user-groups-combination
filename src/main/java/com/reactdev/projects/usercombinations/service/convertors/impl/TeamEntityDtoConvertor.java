package com.reactdev.projects.usercombinations.service.convertors.impl;

import com.reactdev.projects.usercombinations.repository.entities.TeamEntity;
import com.reactdev.projects.usercombinations.service.convertors.ReverseEntityDtoConvertor;
import com.reactdev.projects.usercombinations.web.dto.Team;
import org.springframework.stereotype.Component;

@Component
public class TeamEntityDtoConvertor implements ReverseEntityDtoConvertor<TeamEntity, Team> {
  @Override
  public Team convertEntityToDto(TeamEntity entity) {
    Team group = new Team();

    group.setId(entity.getId());
    group.setName(entity.getName());

    return group;
  }

  @Override
  public TeamEntity dtoToEntity(Team dto) {
    TeamEntity teamEntity = new TeamEntity();
    teamEntity.setId(dto.getId());
    teamEntity.setName(dto.getName());
    return teamEntity;
  }
}
