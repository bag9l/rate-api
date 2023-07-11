package com.rate.api.controller;

import com.rate.api.dto.LecturerProfile;
import com.rate.api.dto.NewStatistic;
import com.rate.api.dto.UpdateLecturerData;
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

import java.io.IOException;

@RequiredArgsConstructor
@RequestMapping("lecturers")
@RestController
public class LecturerController {

    private final LecturerService lecturerService;
    private final StatisticService statisticService;


    @PostMapping("{id}")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<Void> addResponseForLecturer(@AuthenticationPrincipal UserDetails student,
                                                                  @PathVariable("id") String id,
                                                                  @Valid @RequestBody NewStatistic statistic) {
        statisticService.addStatistic(statistic,student.getUsername(), id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value = "/{id}",
            consumes = {"multipart/form-data"})
    public ResponseEntity<Void> setAvatar(@ModelAttribute UpdateLecturerData updateLecturerData,
                                       @PathVariable("id") String userId) throws IOException {
        lecturerService.updateLecturer(updateLecturerData, userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
