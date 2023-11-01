package com.reactdev.projects.usercombinations.service.services;

import com.reactdev.projects.usercombinations.web.dto.Mark;
import com.reactdev.projects.usercombinations.web.dto.User;

import java.time.LocalDate;
import java.util.List;

public interface MarkService {

    Mark addMark(int mark, User user);
    List<Mark> findMarks(LocalDate date, User user);

}
