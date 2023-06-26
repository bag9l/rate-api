package com.rate.api.controller;

import com.rate.api.dto.UpdateUserData;
import com.rate.api.service.AvatarService;
import com.rate.api.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RequestMapping("student")
@RestController
public class StudentController {

    private final StudentService studentService;
    private final AvatarService avatarService;


    @PutMapping("/{id}")
    public ResponseEntity<?> setAvatar(@RequestBody UpdateUserData updateUserData,
                                       @PathVariable("id") String userId) throws IOException {
        avatarService.uploadAvatar(updateUserData.image(), userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
