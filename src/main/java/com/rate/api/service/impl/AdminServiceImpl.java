package com.rate.api.service.impl;

import com.rate.api.exception.EntityNotExistsException;
import com.rate.api.model.Admin;
import com.rate.api.model.Department;
import com.rate.api.repository.AdminRepository;
import com.rate.api.repository.LecturerRepository;
import com.rate.api.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final LecturerRepository lecturerRepository;


    @Override
    public List<Department> getDepartmentsOfUserFacultyByUserLogin(String login) {
        Admin admin = adminRepository.findAdminByLogin(login).orElseThrow(()->
                new EntityNotExistsException("User with login:"+login+" not found"));
        return lecturerRepository.findDepartmentsByFacultyId(admin.getFaculty().getId());
    }
}
