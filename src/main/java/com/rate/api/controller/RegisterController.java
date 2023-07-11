package com.rate.api.controller;

import com.rate.api.dto.auth.AuthenticationResponse;
import com.rate.api.dto.auth.register.LecturerRegisterData;
import com.rate.api.dto.auth.register.LecturerRegisterRequest;
import com.rate.api.dto.auth.register.StudentRegisterData;
import com.rate.api.dto.auth.register.StudentRegisterRequest;
import com.rate.api.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@PreAuthorize(value = "hasAuthority('ADMIN')")
@RequestMapping("register")
@RestController
public class RegisterController {

    private final AuthenticationService authenticationService;

    @PostMapping("student")
    public ResponseEntity<AuthenticationResponse> registerStudent(@Valid @RequestBody StudentRegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                authenticationService.registerStudent(request)
        );
    }

    @PostMapping("lecturer")
    public ResponseEntity<AuthenticationResponse> registerLecturer(@Valid @RequestBody LecturerRegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                authenticationService.registerLecturer(request)
        );
    }

    @GetMapping("student")
    public ResponseEntity<StudentRegisterData> getRegisterDataForStudent(@AuthenticationPrincipal UserDetails admin) {
        return ResponseEntity.status(HttpStatus.OK).body(
                authenticationService.getStudentRegisterData(admin.getUsername())
        );
    }

    @GetMapping("lecturer")
    public ResponseEntity<LecturerRegisterData> getRegisterDataForLecturer(@AuthenticationPrincipal UserDetails admin) {
        return ResponseEntity.status(HttpStatus.OK).body(
                authenticationService.getLecturerRegisterData(admin.getUsername())
        );
    }
}
