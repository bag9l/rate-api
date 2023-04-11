package com.rate.api.service;

import com.rate.api.dto.AuthenticationRequest;
import com.rate.api.dto.AuthenticationResponse;
import com.rate.api.dto.LecturerRegisterRequest;
import com.rate.api.dto.StudentRegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse registerStudent(StudentRegisterRequest request);
    AuthenticationResponse registerLecturer(LecturerRegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
