package com.reactdev.projects.usercombinations.service.services.impl;

import com.reactdev.projects.usercombinations.repository.entities.UserEntity;
import com.reactdev.projects.usercombinations.repository.repositories.UserRepository;
import com.reactdev.projects.usercombinations.service.convertors.EntityDtoConvertor;
import com.reactdev.projects.usercombinations.service.services.UserService;
import com.reactdev.projects.usercombinations.web.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final EntityDtoConvertor<UserEntity, User> entityDtoConvertor;

    @Override
    public List<User> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        return entityDtoConvertor.convertEntityToDto(users);
    }

    @Override
    public User findUser(long id) {
        UserEntity userEntity = userRepository.findById(id).get();
        return entityDtoConvertor.convertEntityToDto(userEntity);
    }
}
