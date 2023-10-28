package com.reactdev.projects.usercombinations.service.convertors.impl;

import com.reactdev.projects.usercombinations.repository.entities.TeamEntity;
import com.reactdev.projects.usercombinations.web.dto.Team;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TeamEntityDtoConvertorTest {

    private final TeamEntityDtoConvertor converter = new TeamEntityDtoConvertor();

    @Test
    public void testConvertEntityToDto() {
        TeamEntity entity = new TeamEntity();
        entity.setId(1L);
        entity.setName("Orange team");
        entity.setUsers(new ArrayList<>());

        Team group = converter.convertEntityToDto(entity);

        assertEquals(entity.getId(), group.getId());
        assertEquals(entity.getName(), group.getName());
    }

    @Test
    public void testDtoToEntity() {
        Team dto = new Team();
        dto.setId(1L);
        dto.setName("Orange team");

        TeamEntity teamEntity = converter.dtoToEntity(dto);

        assertEquals(dto.getId(), teamEntity.getId());
        assertEquals(dto.getName(), teamEntity.getName());
        assertEquals(new ArrayList<>(), teamEntity.getUsers());
    }
}