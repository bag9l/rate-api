package com.rate.api.service.impl;

import com.rate.api.dto.AuthenticationRequest;
import com.rate.api.dto.AuthenticationResponse;
import com.rate.api.dto.LecturerRegisterRequest;
import com.rate.api.dto.StudentRegisterRequest;
import com.rate.api.exception.EntityNotExistsException;
import com.rate.api.model.*;
import com.rate.api.repository.DepartmentRepository;
import com.rate.api.repository.GroupRepository;
import com.rate.api.repository.LecturerRepository;
import com.rate.api.repository.StudentRepository;
import com.rate.api.service.AuthenticationService;
import com.rate.api.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final LecturerRepository lecturerRepository;
    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;
    private final GroupRepository groupRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @Override
    public AuthenticationResponse registerStudent(StudentRegisterRequest request) {

//        Group group = groupRepository.findById(request.groupId()).orElseThrow(() ->
//                new EntityNotExistsException("Group with id:" + request.groupId() + " not found"));

        User user = new Student(
                request.login(),
                passwordEncoder.encode(request.password()),
                request.fullName(),
                request.email(),
                request.course(),
                null,
                false,
                false,
                false,
                true
        );

        studentRepository.save(user);

        String jwt = jwtService.generateToken(user);
        return new AuthenticationResponse(jwt);
    }

    @Override
    public AuthenticationResponse registerLecturer(LecturerRegisterRequest request) {

        Department department = departmentRepository.findById(request.departmentId()).orElseThrow(() ->
                new EntityNotExistsException("Department with id:" + request.departmentId() + " not found"));

        User user = new Lecturer(
                request.login(),
                passwordEncoder.encode(request.password()),
                request.fullName(),
                request.email(),
                null,
                request.degree(),
                department,
                null,
                false,
                false,
                false,
                true
        );

        lecturerRepository.save(user);

        String jwt = jwtService.generateToken(user);
        return new AuthenticationResponse(jwt);
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        System.out.println("IN AUTHENTICATION SERVICE");
        System.out.println("////////////////////////////////////////////////////////////");
        System.out.println(request);
        System.out.println("////////////////////////////////////////////////////////////");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.login(),
                        request.password()
                )
        );

        User user = studentRepository.findStudentByLogin(request.login()).orElse(null);
        if (user == null) {
            user = lecturerRepository.findLecturerByLogin(request.login())
                    .orElseThrow(() ->
                            new EntityNotExistsException("User with username: " + request.login() + " not found"));

        }

//        User user = userRepository.findByLogin(request.getLogin()).orElseThrow(() ->
//                new EntityNotExistsException("User with username:" + request.getLogin() + " not found"));

        String jwt = jwtService.generateToken(user);
        return new AuthenticationResponse(jwt);
    }
}
