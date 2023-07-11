package com.rate.api.controller;

import com.rate.api.dto.NewStatistic;
import com.rate.api.service.LecturerService;
import com.rate.api.service.StatisticService;
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
    private final StatisticService statisticService;


    @PostMapping("{id}")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<Void> addResponseForLecturer(@AuthenticationPrincipal UserDetails student,
                                                       @PathVariable("id") String lecturerId,
                                                       @Valid @RequestBody NewStatistic statistic) {
        statisticService.addStatistic(statistic, student.getUsername(), lecturerId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
