package com.reactdev.projects.usercombinations.service.services;

import com.reactdev.projects.usercombinations.web.dto.User;

import java.util.List;

public interface UsersService {

    User assessUser(Long id, Integer mark);

    List<Integer> findAllMarks(Long id);
}
