package com.reactdev.projects.usercombinations.service.convertors.impl;

import com.reactdev.projects.usercombinations.repository.entities.GroupEntity;
import com.reactdev.projects.usercombinations.service.convertors.EntityDtoConvertor;
import com.reactdev.projects.usercombinations.web.dto.Group;
import org.springframework.stereotype.Component;

@Component
public class GroupEntityDtoConvertor implements EntityDtoConvertor<GroupEntity, Group> {
  @Override
  public Group convertEntityToDto(GroupEntity entity) {
    Group group = new Group();

    group.setId(entity.getId());
    group.setName(entity.getName());

    return group;
  }
}
