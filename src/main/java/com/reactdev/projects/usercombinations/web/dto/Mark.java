package com.reactdev.projects.usercombinations.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class Mark {
    private Long id;
    private LocalDate date;
    private int mark;
}
