package com.rate.api.service;

import com.rate.api.dto.*;
import com.rate.api.model.User;

public interface AuthenticationService {
    AuthenticationResponse registerStudent(StudentRegisterRequest request);
    AuthenticationResponse registerLecturer(LecturerRegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);

    AuthenticatedUser getAuthenticatedUser(String login);
}
