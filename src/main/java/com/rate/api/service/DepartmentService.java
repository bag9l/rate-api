package com.rate.api.service;

import com.rate.api.dto.DepartmentContent;

import java.util.List;

public interface DepartmentService {

    List<DepartmentContent> getDepartmentsContentOfFacultyOfUserByLogin(String facultyId);
}
