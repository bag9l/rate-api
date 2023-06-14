package com.rate.api.dto.auth.register;

import com.rate.api.model.Degree;
import com.rate.api.model.Role;

public record LecturerRegisterRequest(
        String login,
        String password,
        Role role,
        String fullName,
        String email,
        Degree degree,
        String departmentId
        ) {
}
