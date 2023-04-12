package com.rate.api.controller;

import com.rate.api.model.Lecturer;
import com.rate.api.model.Subject;
import com.rate.api.model.User;
import com.rate.api.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("subjects")
@RestController
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping("{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable("id")String id){
        return ResponseEntity.status(HttpStatus.OK).body(
                subjectService.getSubjectById(id)
        );
    }

    @GetMapping()
    public ResponseEntity<List<Subject>> getSubjects(@AuthenticationPrincipal User user){
        return ResponseEntity.status(HttpStatus.OK).body(
                subjectService.getSubjectsByLogin(user.getLogin())
        );
    }
}
