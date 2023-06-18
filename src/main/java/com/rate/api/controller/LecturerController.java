package com.rate.api.controller;

import com.rate.api.dto.LecturerProfile;
import com.rate.api.dto.NewStatistic;
import com.rate.api.model.Statistic;
import com.rate.api.service.LecturerService;
import com.rate.api.service.StatisticService;
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


    @GetMapping("{id}")
    public ResponseEntity<LecturerProfile> getLecturerById(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                lecturerService.getLecturerById(id)
        );
    }

    @PostMapping("{id}")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<LecturerProfile> addResponseForLecturer(@AuthenticationPrincipal UserDetails student,
                                                                  @PathVariable("id") String id,
                                                                  @RequestBody NewStatistic statistic) {
        statisticService.addStatistic(statistic,student.getUsername(), id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
