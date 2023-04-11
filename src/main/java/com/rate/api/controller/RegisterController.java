package com.rate.api.controller;

import com.rate.api.dto.AuthenticationResponse;
import com.rate.api.dto.LecturerRegisterRequest;
import com.rate.api.dto.StudentRegisterRequest;
import com.rate.api.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("register")
@RestController
public class RegisterController {

    private final AuthenticationService authenticationService;

    @PostMapping("student")
    public ResponseEntity<AuthenticationResponse> registerStudent(@RequestBody StudentRegisterRequest request){
        System.out.println("////////////////////////////////////////////////////////////////////////");
        System.out.println("////////////////////////////////////////////////////////////////////////");
        System.out.println(request);
        System.out.println("////////////////////////////////////////////////////////////////////////");
        System.out.println("////////////////////////////////////////////////////////////////////////");
        return ResponseEntity.status(HttpStatus.CREATED).body(
                authenticationService.registerStudent(request)
        );
    }

    @PostMapping("lecturer")
    public ResponseEntity<AuthenticationResponse> registerLecturer(@RequestBody LecturerRegisterRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                authenticationService.registerLecturer(request)
        );
    }
}
