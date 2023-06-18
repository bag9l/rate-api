package com.rate.api.controller;

import com.rate.api.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("admin")
@RestController
public class AdminController {

    private final AdminService adminService;

}
