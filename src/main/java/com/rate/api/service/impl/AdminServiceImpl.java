package com.rate.api.service.impl;

import com.rate.api.dto.DepartmentDto;
import com.rate.api.exception.EntityNotExistsException;
import com.rate.api.mapper.DepartmentMapper;
import com.rate.api.model.user.Admin;
import com.rate.api.repository.AdminRepository;
import com.rate.api.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    private final DepartmentMapper departmentMapper;


    @Override
    public List<DepartmentDto> getDepartmentsOfFacultyByAdminLogin(String login) {
        Admin admin = adminRepository.findAdminByLogin(login).orElseThrow(() ->
                new EntityNotExistsException("User with login:" + login + " not found"));
        return adminRepository.findDepartmentsByFacultyId(admin.getFaculty().getId()).stream()
                .map(departmentMapper::departmentToDto)
                .collect(Collectors.toList());
    }
}
