package com.rate.api.service.impl;

import com.rate.api.dto.AuthenticationRequest;
import com.rate.api.dto.AuthenticationResponse;
import com.rate.api.dto.LecturerRegisterRequest;
import com.rate.api.dto.StudentRegisterRequest;
import com.rate.api.exception.EntityNotExistsException;
import com.rate.api.model.Lecturer;
import com.rate.api.model.Student;
import com.rate.api.model.User;
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
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @Override
    public AuthenticationResponse registerStudent(StudentRegisterRequest request) {

        User user = new Student(
                request.login(),
                passwordEncoder.encode(request.password()),
                request.fullName(),
                request.group(),
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

        User user = new Lecturer(
                request.login(),
                passwordEncoder.encode(request.password()),
                request.fullName(),
                request.degree(),
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
        System.out.println("////////////////////////////////////////////////////////////");
        System.out.println(request);
        System.out.println("////////////////////////////////////////////////////////////");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.login(),
                        request.password()
                )
        );

        User user = studentRepository.findByLogin(request.login()).orElse(null);
        if (user == null) {
            user = lecturerRepository.findByLogin(request.login())
                    .orElseThrow(() ->
                            new EntityNotExistsException("User with username: " + request.login() + " not found"));

        }

//        User user = userRepository.findByLogin(request.getLogin()).orElseThrow(() ->
//                new EntityNotExistsException("User with username:" + request.getLogin() + " not found"));

        String jwt = jwtService.generateToken(user);
        return new AuthenticationResponse(jwt);
//                .builder()
//                .token(jwt)
//                .build();
    }
}
