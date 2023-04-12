package com.rate.api.controller;

import com.rate.api.dto.AuthenticationRequest;
import com.rate.api.dto.AuthenticationResponse;
import com.rate.api.model.Role;
import com.rate.api.service.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping(path = "login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(
                authenticationService.authenticate(request)
        );
    }

    @GetMapping("/loginSuccess")
    public void getLoginInfo(@AuthenticationPrincipal UserDetails authentication,
                             HttpServletResponse response) throws IOException {
        if (authentication.getAuthorities()
                .contains(new SimpleGrantedAuthority(Role.STUDENT.name()))) {
            response.sendRedirect("/student/subjects");
        } else if (authentication.getAuthorities()
                .contains(new SimpleGrantedAuthority(Role.STUDENT.name()))) {
            response.sendRedirect("/lecturer/subjects");
        } else if (authentication.getAuthorities()
                .contains(new SimpleGrantedAuthority(Role.ADMIN.name()))) {
            response.sendRedirect("/admin/departments");
        }
    }
}
