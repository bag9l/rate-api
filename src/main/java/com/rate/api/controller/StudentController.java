package com.rate.api.controller;

import com.rate.api.dto.UpdateStudentData;
import com.rate.api.service.AvatarService;
import com.rate.api.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@RequestMapping("student")
@RestController
public class StudentController {

    private final StudentService studentService;

    @PutMapping(value = "/{id}",
            consumes = {"multipart/form-data"})
    public ResponseEntity<Void> setAvatar(@ModelAttribute UpdateStudentData updateStudentData,
                                       @PathVariable("id") String userId) throws IOException {
        studentService.updateStudent(updateStudentData, userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

//    @GetMapping("/{id}/image")
//    public ResponseEntity<?> getStudentImage(@PathVariable("id") String userId) {
//        return ResponseEntity.status(HttpStatus.OK)
//                .contentType(MediaType.valueOf(MediaType.IMAGE_PNG_VALUE))
//                .body(avatarService.getUserAvatar(userId));
//    }
}
