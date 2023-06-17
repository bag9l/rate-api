package com.rate.api.controller;

import com.rate.api.dto.NewEducationalMethod;
import com.rate.api.dto.NewSubject;
import com.rate.api.dto.SubjectDto;
import com.rate.api.model.Subject;
import com.rate.api.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("subjects")
@RestController
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping("{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                subjectService.getSubjectById(id)
        );
    }

    @PostMapping("{id}")
    @PreAuthorize("hasAuthority('LECTURER')")
    public ResponseEntity<Void> addEducationalMethod(@PathVariable("id") String id,
                                                     @RequestBody NewEducationalMethod educationalMethod) {
        subjectService.addEducationalMethodToSubject(id, educationalMethod);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping()
    @PreAuthorize("hasAnyAuthority({'STUDENT','LECTURER'})")
    public ResponseEntity<List<SubjectDto>> getSubjects(@AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.status(HttpStatus.OK).body(
                subjectService.getSubjectsByUserLogin(user.getUsername())
        );
    }

    @PostMapping("new")
    @PreAuthorize("hasAuthority('LECTURER')")
    public ResponseEntity<String> addSubject(@AuthenticationPrincipal UserDetails user,
                                             @RequestBody NewSubject newSubject) {
        return ResponseEntity.status(HttpStatus.OK).body(
                subjectService.addSubjectForLecturer(user.getUsername(), newSubject)
        );
    }

}
