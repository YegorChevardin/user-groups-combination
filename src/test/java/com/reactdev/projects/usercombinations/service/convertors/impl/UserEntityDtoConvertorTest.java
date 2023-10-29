package com.reactdev.projects.usercombinations.service.convertors.impl;

import com.reactdev.projects.usercombinations.repository.entities.TeamEntity;
import com.reactdev.projects.usercombinations.repository.entities.UserEntity;
import com.reactdev.projects.usercombinations.service.convertors.ReverseEntityDtoConvertor;
import com.reactdev.projects.usercombinations.web.dto.Team;
import com.reactdev.projects.usercombinations.web.dto.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserEntityDtoConvertorTest {

    @Mock
    private ReverseEntityDtoConvertor<TeamEntity, Team> teamEntityDtoConvertor;

    @InjectMocks
    private UserEntityDtoConvertor convertor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConvertEntityToDto() {
        TeamEntity teamEntity = new TeamEntity();
        Team team = new Team();

        UserEntity entity = new UserEntity();
        entity.setId(1L);
        entity.setName("John");
        entity.setSecondName("Doe");
        entity.setTeam(teamEntity);

        when(teamEntityDtoConvertor.convertEntityToDto(teamEntity)).thenReturn(team);

        User dto = convertor.convertEntityToDto(entity);

        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getName(), dto.getName());
        assertEquals(entity.getSecondName(), dto.getSecondName());
        assertEquals(team, dto.getTeam());
    }

    @Test
    void testDtoToEntity() {
        Team team = new Team();
        TeamEntity teamEntity = new TeamEntity();

        User dto = new User();
        dto.setId(1L);
        dto.setName("John");
        dto.setSecondName("Doe");
        dto.setTeam(team);

        when(teamEntityDtoConvertor.dtoToEntity(team)).thenReturn(teamEntity);

        UserEntity entity = convertor.dtoToEntity(dto);

        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getName(), entity.getName());
        assertEquals(dto.getSecondName(), entity.getSecondName());
        assertEquals(teamEntity, entity.getTeam());
    }


}