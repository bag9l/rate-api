package com.rate.api.controller;

import com.rate.api.model.Admin;
import com.rate.api.model.Department;
import com.rate.api.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("admin")
@RestController
public class AdminController {

    private final AdminService adminService;


    @GetMapping("departments")
    public ResponseEntity<List<Department>> getSubjects(@AuthenticationPrincipal Admin admin){
        return ResponseEntity.status(HttpStatus.OK).body(
                adminService.getDepartmentsOfUserFacultyByUserLogin(admin.getLogin())
        );
    }
}
