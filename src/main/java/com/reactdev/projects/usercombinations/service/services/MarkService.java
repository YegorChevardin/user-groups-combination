package com.reactdev.projects.usercombinations.service.services;

import com.reactdev.projects.usercombinations.web.dto.Mark;

import java.time.LocalDate;
import java.util.List;

public interface MarkService {

    Mark addMark(int mark, long id);
    List<Mark> findMarks(LocalDate date, long id);

}
