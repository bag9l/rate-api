package com.rate.api.controller;

import com.rate.api.dto.NewFeedback;
import com.rate.api.service.LecturerService;
import com.rate.api.service.FeedbackService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("lecturers")
@RestController
public class LecturerController {

    private final LecturerService lecturerService;
    private final FeedbackService feedbackService;


    @PostMapping("{id}")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<Void> addResponseForLecturer(@AuthenticationPrincipal UserDetails student,
                                                       @PathVariable("id") String lecturerId,
                                                       @Valid @RequestBody NewFeedback feedback) {
        feedbackService.addFeedback(feedback, student.getUsername(), lecturerId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
