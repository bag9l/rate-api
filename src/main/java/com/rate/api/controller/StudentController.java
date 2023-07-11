package com.rate.api.controller;

import com.rate.api.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("student")
@RestController
public class StudentController {

    private final StudentService studentService;


//    @GetMapping("/{id}/image")
//    public ResponseEntity<?> getStudentImage(@PathVariable("id") String userId) {
//        return ResponseEntity.status(HttpStatus.OK)
//                .contentType(MediaType.valueOf(MediaType.IMAGE_PNG_VALUE))
//                .body(avatarService.getUserAvatar(userId));
//    }
}
