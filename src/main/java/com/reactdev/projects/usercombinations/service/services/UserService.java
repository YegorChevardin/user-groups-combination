package com.reactdev.projects.usercombinations.service.services;

import com.reactdev.projects.usercombinations.web.dto.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User findUser(long id);
}
