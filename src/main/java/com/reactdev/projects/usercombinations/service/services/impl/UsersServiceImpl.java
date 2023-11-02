package com.reactdev.projects.usercombinations.service.services.impl;

import com.reactdev.projects.usercombinations.repository.entities.UserEntity;
import com.reactdev.projects.usercombinations.repository.repositories.UserRepository;
import com.reactdev.projects.usercombinations.service.convertors.EntityDtoConvertor;
import com.reactdev.projects.usercombinations.service.services.UsersService;
import com.reactdev.projects.usercombinations.web.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UserRepository userRepository;
    private final EntityDtoConvertor<UserEntity, User> convertor;

    @Override
    public User assessUser(Long id, Integer mark) {
        UserEntity userEntity = userRepository.findUserEntityById(id);
        userEntity.getMarks().add(mark);
        return convertor.convertEntityToDto(userRepository.save(userEntity));
    }

    @Override
    public List<Integer> findAllMarks(Long id) {
        return userRepository.findMarksByUserId(id);
    }
}
