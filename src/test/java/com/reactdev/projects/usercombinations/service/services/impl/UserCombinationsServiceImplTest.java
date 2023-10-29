package com.reactdev.projects.usercombinations.service.services.impl;

import com.reactdev.projects.usercombinations.repository.entities.TeamEntity;
import com.reactdev.projects.usercombinations.repository.entities.UserEntity;
import com.reactdev.projects.usercombinations.repository.repositories.UserCombinationsRepository;
import com.reactdev.projects.usercombinations.repository.repositories.UserRepository;
import com.reactdev.projects.usercombinations.service.convertors.impl.UserEntityDtoConvertor;
import com.reactdev.projects.usercombinations.web.dto.Team;
import com.reactdev.projects.usercombinations.web.dto.User;
import com.reactdev.projects.usercombinations.web.exceptions.impl.DataNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


class UserCombinationsServiceImplTest {

    @Mock
    private UserCombinationsRepository userCombinationsRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserEntityDtoConvertor userReverseEntityDtoConvertor;

    @InjectMocks
    private UserCombinationsServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateNewCombinationsWhenBothTeamsEmpty() {
        Mockito.reset(userRepository, userCombinationsRepository, userReverseEntityDtoConvertor);

        when(userRepository.findAllByTeamName(anyString())).thenReturn(Collections.emptyList());

        assertThrows(DataNotFoundException.class, () -> service.generateNewCombinations());

        verify(userCombinationsRepository, never()).deleteAll();
        verify(userCombinationsRepository, never()).saveAll(any());
    }

    @Test
    void testGenerateNewCombinationsWhenFirstTeamEmpty() {
        Mockito.reset(userRepository, userCombinationsRepository, userReverseEntityDtoConvertor);

        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setId(1L);
        teamEntity.setName("Orange team");
        teamEntity.setUsers(new ArrayList<>());

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setName("John");
        userEntity.setSecondName("Doe");
        userEntity.setTeam(teamEntity);

        List<UserEntity> userEntityArrayList = new ArrayList<>();
        userEntityArrayList.add(userEntity);

        Team team = new Team();
        team.setId(1L);
        team.setName("Orange team");

        User user = new User();
        user.setId(1L);
        user.setName("John");
        user.setSecondName("Doe");
        user.setTeam(team);

        List<User> users = new ArrayList<>();
        users.add(user);

        when(userRepository.findAllByTeamName("Orange team")).thenReturn(Collections.emptyList());
        when(userRepository.findAllByTeamName("Purple team")).thenReturn(userEntityArrayList);

        when(userReverseEntityDtoConvertor.convertEntityToDto(userEntityArrayList)).thenReturn(users);

        assertThrows(DataNotFoundException.class, () -> service.generateNewCombinations());

        verify(userCombinationsRepository, never()).deleteAll();
        verify(userCombinationsRepository, never()).saveAll(any());
    }

    @Test
    void testGenerateNewCombinationsWhenSecondTeamEmpty() {
        Mockito.reset(userRepository, userCombinationsRepository, userReverseEntityDtoConvertor);

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setName("John");
        userEntity.setSecondName("Doe");
        userEntity.setTeam(new TeamEntity());

        List<UserEntity> userEntityArrayList = new ArrayList<>();
        userEntityArrayList.add(userEntity);

        User user = new User();
        user.setId(1L);
        user.setName("John");
        user.setSecondName("Doe");
        user.setTeam(new Team());

        List<User> users = new ArrayList<>();
        users.add(user);

        when(userRepository.findAllByTeamName("Orange team")).thenReturn(userEntityArrayList);
        when(userRepository.findAllByTeamName("Purple team")).thenReturn(Collections.emptyList());

        when(userReverseEntityDtoConvertor.convertEntityToDto(userEntityArrayList)).thenReturn(users);

        assertThrows(DataNotFoundException.class, () -> service.generateNewCombinations());

        verify(userCombinationsRepository, never()).deleteAll();
        verify(userCombinationsRepository, never()).saveAll(any());
    }

    @Test
    void testGenerateNewCombinationsWhenAllTeamsPresent() {
        Mockito.reset(userRepository, userCombinationsRepository, userReverseEntityDtoConvertor);

        UserEntity userEntityFirstTeam = new UserEntity();
        userEntityFirstTeam.setId(1L);
        userEntityFirstTeam.setName("John");
        userEntityFirstTeam.setSecondName("Doe");
        userEntityFirstTeam.setTeam(new TeamEntity());

        List<UserEntity> userEntitiesInFirstTeam = new ArrayList<>();
        userEntitiesInFirstTeam.add(userEntityFirstTeam);

        UserEntity userEntitySecondTeam = new UserEntity();
        userEntitySecondTeam.setId(2L);
        userEntitySecondTeam.setName("Jane");
        userEntitySecondTeam.setSecondName("Doe");
        userEntitySecondTeam.setTeam(new TeamEntity());

        List<UserEntity> userEntitiesInSecondTeam = new ArrayList<>();
        userEntitiesInSecondTeam.add(userEntitySecondTeam);

        User userFirstTeam = new User();
        userFirstTeam.setId(1L);
        userFirstTeam.setName("John");
        userFirstTeam.setSecondName("Doe");
        userFirstTeam.setTeam(new Team());

        List<User> usersInFirstTeam = new ArrayList<>();
        usersInFirstTeam.add(userFirstTeam);

        User userSecondTeam = new User();
        userSecondTeam.setId(2L);
        userSecondTeam.setName("Jane");
        userSecondTeam.setSecondName("Doe");
        userSecondTeam.setTeam(new Team());

        List<User> usersInSecondTeam = new ArrayList<>();
        usersInSecondTeam.add(userSecondTeam);

        when(userRepository.findAllByTeamName("Orange team")).thenReturn(userEntitiesInFirstTeam);
        when(userReverseEntityDtoConvertor.convertEntityToDto(userEntitiesInFirstTeam)).thenReturn(usersInFirstTeam);
        when(userRepository.findAllByTeamName("Purple team")).thenReturn(userEntitiesInSecondTeam);
        when(userReverseEntityDtoConvertor.convertEntityToDto(userEntitiesInSecondTeam)).thenReturn(usersInSecondTeam);

        service.generateNewCombinations();

        verify(userCombinationsRepository, times(1)).findAll();
        verify(userCombinationsRepository, times(1)).deleteAll();
        verify(userCombinationsRepository, times(1)).saveAll(any());

        verify(userRepository, times(1)).findAllByTeamName("Orange team");
        verify(userRepository, times(1)).findAllByTeamName("Purple team");

        verify(userReverseEntityDtoConvertor, times(1)).convertEntityToDto(userEntitiesInFirstTeam);
        verify(userReverseEntityDtoConvertor, times(1)).convertEntityToDto(userEntitiesInSecondTeam);
    }

}