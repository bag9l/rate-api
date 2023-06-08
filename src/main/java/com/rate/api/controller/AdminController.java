package com.rate.api.controller;

import com.rate.api.dto.DepartmentDto;
import com.rate.api.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("admin")
@RestController
public class AdminController {

    private final AdminService adminService;


    @GetMapping("my")
    public ResponseEntity<List<DepartmentDto>> getDepartments(@AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.status(HttpStatus.OK).body(
                adminService.getDepartmentsOfFacultyByAdminLogin(user.getUsername())
        );
    }
}
