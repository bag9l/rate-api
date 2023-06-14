package com.rate.api.controller;

import com.rate.api.dto.SubjectDto;
import com.rate.api.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("student")
@RestController
public class StudentController {

    private final StudentService studentService;

//    @GetMapping("my")
//    @PreAuthorize("hasAuthority('STUDENT')")
//    public ResponseEntity<List<SubjectDto>> getSubjects(@AuthenticationPrincipal UserDetails student) {
//        System.out.println("?????? IN GETSUBJECTS");
//        return ResponseEntity.status(HttpStatus.OK).body(
//                studentService.getSubjectsByLogin(student.getUsername())
//        );
//    }

}
