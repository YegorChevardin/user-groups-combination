package com.reactdev.projects.usercombinations.service.convertors.impl;

import com.reactdev.projects.usercombinations.repository.entities.UserCombinationEntity;
import com.reactdev.projects.usercombinations.repository.entities.UserEntity;
import com.reactdev.projects.usercombinations.web.dto.User;
import com.reactdev.projects.usercombinations.web.dto.UserCombination;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserCombinationEntityDtoConvertorTest {
    @Mock
    private UserEntityDtoConvertor userEntityDtoConvertor;

    @InjectMocks
    private UserCombinationEntityDtoConvertor convertor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConvertEntityToDto() {
        UserEntity firstUserEntity = new UserEntity();
        UserEntity secondUserEntity = new UserEntity();
        User firstUser = new User();
        User secondUser = new User();

        UserCombinationEntity entity = new UserCombinationEntity();
        entity.setId(1L);
        entity.setFirstUser(firstUserEntity);
        entity.setSecondUser(secondUserEntity);

        when(userEntityDtoConvertor.convertEntityToDto(firstUserEntity)).thenReturn(firstUser);
        when(userEntityDtoConvertor.convertEntityToDto(secondUserEntity)).thenReturn(secondUser);

        UserCombination dto = convertor.convertEntityToDto(entity);

        assertEquals(entity.getId(), dto.getId());
        assertEquals(firstUser, dto.getFirstUser());
        assertEquals(secondUser, dto.getSecondUser());
    }

    @Test
    void testDtoToEntity() {
        User firstUser = new User();
        User secondUser = new User();
        UserEntity firstUserEntity = new UserEntity();
        UserEntity secondUserEntity = new UserEntity();
        UserCombination dto = new UserCombination();

        firstUser.setId(1L);
        secondUser.setId(2L);
        dto.setId(1L);
        dto.setFirstUser(firstUser);
        dto.setSecondUser(secondUser);

        when(userEntityDtoConvertor.dtoToEntity(firstUser)).thenReturn(firstUserEntity);
        when(userEntityDtoConvertor.dtoToEntity(secondUser)).thenReturn(secondUserEntity);

        UserCombinationEntity entity = convertor.dtoToEntity(dto);

        assertEquals(dto.getId(), entity.getId());
        assertEquals(firstUserEntity, entity.getFirstUser());
        assertEquals(secondUserEntity, entity.getSecondUser());
    }
}