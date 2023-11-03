package com.reactdev.projects.usercombinations.service.convertors.impl;

import com.reactdev.projects.usercombinations.repository.entities.MarkEntity;
import com.reactdev.projects.usercombinations.service.convertors.EntityDtoConvertor;
import com.reactdev.projects.usercombinations.web.dto.Mark;
import org.springframework.stereotype.Component;

@Component
public class MarkEntityDtoConvertor implements EntityDtoConvertor<MarkEntity, Mark> {

    @Override
    public Mark convertEntityToDto(MarkEntity entity) {
        Mark mark = new Mark();
        mark.setId(entity.getId());
        mark.setDate(entity.getDate());
        mark.setMark(entity.getMark());
        return mark;
    }
}

