package com.reactdev.projects.usercombinations.web.controllers;

import com.reactdev.projects.usercombinations.service.services.UsersService;
import com.reactdev.projects.usercombinations.web.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @PostMapping("/assess/{id}")
    public ResponseEntity<User> assessUser(@PathVariable String id, @RequestParam Integer mark) {
        return ResponseEntity.ok(usersService.assessUser(Long.parseLong(id), mark));
    }

    @GetMapping("/marks/{id}")
    public ResponseEntity<List<Integer>> getAllMarks(@PathVariable String id){
        return ResponseEntity.ok(usersService.findAllMarks(Long.parseLong(id)));
    }
}
