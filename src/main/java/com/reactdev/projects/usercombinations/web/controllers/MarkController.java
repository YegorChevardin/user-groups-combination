package com.reactdev.projects.usercombinations.web.controllers;

import com.reactdev.projects.usercombinations.service.services.MarkService;
import com.reactdev.projects.usercombinations.web.dto.Mark;
import com.reactdev.projects.usercombinations.web.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v2/marks")
@RequiredArgsConstructor
public class MarkController {
    private final MarkService markService;

    @PostMapping
    public ResponseEntity<Mark> addMark(@RequestParam int mark, @RequestBody User user) {
        Mark response = markService.addMark(mark, user);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{date}")
    public ResponseEntity<List<Mark>> findMarks (@PathVariable LocalDate date, @RequestBody User user) {
        List<Mark> marks = markService.findMarks(date, user);
        return ResponseEntity.ok(marks);
    }



}
