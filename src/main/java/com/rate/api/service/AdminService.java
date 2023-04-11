package com.rate.api.service;

import com.rate.api.model.Department;

import java.util.List;

public interface AdminService {

    List<Department> getDepartmentsOfUserFacultyByUserLogin(String login);
}
