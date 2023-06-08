package com.rate.api.service;

import com.rate.api.dto.DepartmentDto;

import java.util.List;

public interface AdminService {

    List<DepartmentDto> getDepartmentsOfFacultyByAdminLogin(String login);
}
