package com.rate.api.controller;

import com.rate.api.model.Student;
import com.rate.api.model.Subject;
import com.rate.api.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("student")
@RestController
public class StudentController {

    private final StudentService studentService;

    @GetMapping("subjects")
//    public ResponseEntity<List<Subject>> getSubjects(Authentication authentication){
    public ResponseEntity<List<Subject>> getSubjects(@AuthenticationPrincipal Student student){
        return ResponseEntity.status(HttpStatus.OK).body(
                studentService.getSubjectsByLogin(student.getLogin())
        );
    }

}
