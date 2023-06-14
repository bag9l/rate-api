package com.rate.api.controller;

import com.rate.api.dto.SubjectDto;
import com.rate.api.model.Lecturer;
import com.rate.api.model.User;
import com.rate.api.service.LecturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("lecturer")
@PreAuthorize("hasRole('LECTURER')")
@RestController
public class LecturerController {

    private final LecturerService lecturerService;

//    @GetMapping("my")
//    public ResponseEntity<List<SubjectDto>> getSubjects(@AuthenticationPrincipal UserDetails lecturer) {
//        return ResponseEntity.status(HttpStatus.OK).body(
//                lecturerService.getSubjectsByLogin(lecturer.getUsername())
//        );
//    }

//    @GetMapping()
//    public ResponseEntity<List<Lecturer>> getLecturers(@AuthenticationPrincipal User user) {
//        return ResponseEntity.status(HttpStatus.OK).body(
//                lecturerService.getLectors(user.getLogin())
//        );
//    }


    @GetMapping("{id}")
    public ResponseEntity<Lecturer> getLecturerById(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                lecturerService.getLecturerById(id)
        );
    }
}
