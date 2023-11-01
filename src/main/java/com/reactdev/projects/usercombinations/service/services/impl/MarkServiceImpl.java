package com.reactdev.projects.usercombinations.service.services.impl;

import com.reactdev.projects.usercombinations.repository.entities.MarkEntity;
import com.reactdev.projects.usercombinations.repository.entities.UserEntity;
import com.reactdev.projects.usercombinations.repository.repositories.MarkRepository;
import com.reactdev.projects.usercombinations.repository.repositories.UserRepository;
import com.reactdev.projects.usercombinations.service.convertors.EntityDtoConvertor;
import com.reactdev.projects.usercombinations.service.services.MarkService;
import com.reactdev.projects.usercombinations.web.dto.Mark;
import com.reactdev.projects.usercombinations.web.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MarkServiceImpl implements MarkService {
    private final MarkRepository markRepository;
    private final UserRepository userRepository;
    private final EntityDtoConvertor<MarkEntity, Mark> markEntityMarkEntityDtoConvertor;

    @Override
    public Mark addMark(int mark, User user) {
        UserEntity userEntity = userRepository.findByNameAndSecondName(
                user.getName(), user.getSecondName()).get();
        MarkEntity markEntity = new MarkEntity();
        markEntity.setDate(LocalDate.now());
        markEntity.setMark(mark);
        markEntity.setUser(userEntity);
        MarkEntity returnedMarkEntity = markRepository.saveAndFlush(markEntity);
        return markEntityMarkEntityDtoConvertor.convertEntityToDto(returnedMarkEntity);
    }

    @Override
    public List<Mark> findMarks(LocalDate date, User user) {
        UserEntity userEntity = userRepository.findById(user.getId()).get();
        List<MarkEntity> markEntities = markRepository.findAllByDateAndUser(date, userEntity);
        return markEntityMarkEntityDtoConvertor.convertEntityToDto(markEntities);
    }
}
