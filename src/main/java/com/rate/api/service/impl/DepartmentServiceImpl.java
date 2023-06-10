package com.rate.api.service.impl;

import com.rate.api.dto.DepartmentContent;
import com.rate.api.exception.EntityNotExistsException;
import com.rate.api.mapper.DepartmentMapper;
import com.rate.api.model.Department;
import com.rate.api.model.Faculty;
import com.rate.api.repository.DepartmentRepository;
import com.rate.api.repository.FacultyRepository;
import com.rate.api.repository.UserRepository;
import com.rate.api.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    private final FacultyRepository facultyRepository;

    @Transactional
    @Override
    public List<DepartmentContent> getDepartmentsContentOfFacultyOfUserByLogin(String login) {

        Faculty faculty = facultyRepository.findFacultyForStudentByLogin(login)
                .orElseGet(() -> facultyRepository.findFacultyForLecturerByLogin(login)
                        .orElseGet(() -> facultyRepository.findFacultyForAdminByLogin(login).orElseThrow(() ->
                                new EntityNotExistsException("Faculty not found"))));

        List<Department> departments = departmentRepository.findDepartmentsByFacultyId(faculty.getId());

        return departments.stream()
                .map(departmentMapper::departmentToDepartmentContent)
                .collect(Collectors.toList());
    }
}
