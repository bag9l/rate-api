package com.rate.api.controller;

import com.rate.api.dto.UserProfile;
import com.rate.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("user")
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("profile/{id}")
    public ResponseEntity<UserProfile> getUserById(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                userService.getUserProfileById(id)
        );
    }
}
