package com.reactdev.projects.usercombinations.web.controllers;

import com.reactdev.projects.usercombinations.service.services.MarkService;
import com.reactdev.projects.usercombinations.web.dto.Mark;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v2/users")
@RequiredArgsConstructor
public class MarkController {
    private final MarkService markService;

    @PostMapping("/{id}/marks/{mark}")
    public ResponseEntity<Mark> addMark(@PathVariable int mark, @PathVariable long id) {
        Mark response = markService.addMark(mark, id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/marks/{date}")
    public ResponseEntity<List<Mark>> findMarks (@PathVariable LocalDate date, @PathVariable long id) {
        List<Mark> marks = markService.findMarks(date, id);
        return ResponseEntity.ok(marks);
    }



}
