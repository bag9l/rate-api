package com.rate.api.service.impl;

import com.rate.api.dto.CourseDto;
import com.rate.api.dto.DepartmentDto;
import com.rate.api.dto.auth.AuthenticatedUser;
import com.rate.api.dto.auth.AuthenticationRequest;
import com.rate.api.dto.auth.AuthenticationResponse;
import com.rate.api.dto.auth.register.LecturerRegisterData;
import com.rate.api.dto.auth.register.LecturerRegisterRequest;
import com.rate.api.dto.auth.register.StudentRegisterData;
import com.rate.api.dto.auth.register.StudentRegisterRequest;
import com.rate.api.exception.EntityNotExistsException;
import com.rate.api.mapper.CourseMapper;
import com.rate.api.mapper.DepartmentMapper;
import com.rate.api.mapper.UserMapper;
import com.rate.api.model.Course;
import com.rate.api.model.Degree;
import com.rate.api.model.Department;
import com.rate.api.model.Group;
import com.rate.api.model.user.Admin;
import com.rate.api.model.user.Lecturer;
import com.rate.api.model.user.Student;
import com.rate.api.model.user.User;
import com.rate.api.repository.*;
import com.rate.api.service.AuthenticationService;
import com.rate.api.service.JwtService;
import com.rate.api.service.UserService;
import com.rate.api.token.Token;
import com.rate.api.token.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final LecturerRepository lecturerRepository;
    private final StudentRepository studentRepository;
    private final AdminRepository adminRepository;
    private final TokenRepository tokenRepository;
    private final DepartmentRepository departmentRepository;
    private final CourseRepository courseRepository;
    private final GroupRepository groupRepository;

    private final UserMapper userMapper;
    private final CourseMapper courseMapper;
    private final DepartmentMapper departmentMapper;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;


    @Override
    public AuthenticationResponse registerStudent(StudentRegisterRequest request) {

        Group group = groupRepository.findById(request.groupId()).orElseThrow(() ->
                new EntityNotExistsException("Group with id:" + request.groupId() + " not found"));

        User user = new Student(
                request.login(),
                passwordEncoder.encode(request.password()),
                request.fullName(),
                request.email(),
                group,
                false,
                false,
                false,
                true
        );

        User savedUser = studentRepository.save(user);
        String jwt = jwtService.generateToken(user);

        saveUserToken(savedUser, jwt);

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

        User savedUser = lecturerRepository.save(user);
        String jwt = jwtService.generateToken(user);

        saveUserToken(savedUser, jwt);

        return new AuthenticationResponse(jwt);
    }

    @Transactional
    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.login(),
                        request.password()
                )
        );

        User user = userService.findUserByLogin(request.login());

        String jwt = jwtService.generateToken(user);

        revokeAllUserTokens(user);
        saveUserToken(user, jwt);

        return new AuthenticationResponse(jwt);
    }

    @Override
    public AuthenticatedUser getAuthenticatedUser(String login) {
        User user = userService.findUserByLogin(login);
        return userMapper.userToAuthenticatedUser(user);
    }

    @Override
    public StudentRegisterData getStudentRegisterData(String adminLogin) {
        Admin admin = adminRepository.findAdminByLogin(adminLogin).orElseThrow(() ->
                new EntityNotExistsException("User with login:" + adminLogin + " not found"));
        List<Course> courses = courseRepository.findAllCoursesForFacultyById(admin.getFaculty().getId());
        List<CourseDto> courseDtos = courses.stream()
                .map(courseMapper::courseToDto)
                .collect(Collectors.toList());

        return new StudentRegisterData(courseDtos);
    }

    @Override
    public LecturerRegisterData getLecturerRegisterData(String adminLogin) {
        Admin admin = adminRepository.findAdminByLogin(adminLogin).orElseThrow(() ->
                new EntityNotExistsException("User with login:" + adminLogin + " not found"));

        List<Department> departments = departmentRepository.findDepartmentsByFacultyId(admin.getFaculty().getId());
        List<DepartmentDto> departmentDtos = departments.stream()
                .map(departmentMapper::departmentToDto)
                .collect(Collectors.toList());

        List<String> degrees = List.of(Arrays.toString(Degree.values()));

        return new LecturerRegisterData(degrees, departmentDtos);
    }


    private void saveUserToken(User user, String jwtToken) {
        Token token = new Token(jwtToken, false, false, user);
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokensForUser(user.getLogin());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setIsExpired(true);
            token.setIsRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

}
