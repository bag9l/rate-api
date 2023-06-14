package com.rate.api.service;

import com.rate.api.dto.auth.AuthenticatedUser;
import com.rate.api.dto.auth.AuthenticationRequest;
import com.rate.api.dto.auth.AuthenticationResponse;
import com.rate.api.dto.auth.register.LecturerRegisterData;
import com.rate.api.dto.auth.register.LecturerRegisterRequest;
import com.rate.api.dto.auth.register.StudentRegisterData;
import com.rate.api.dto.auth.register.StudentRegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse registerStudent(StudentRegisterRequest request);
    AuthenticationResponse registerLecturer(LecturerRegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
    AuthenticatedUser getAuthenticatedUser(String login);
    StudentRegisterData getStudentRegisterData(String adminLogin);
    LecturerRegisterData getLecturerRegisterData(String adminLogin);
}
