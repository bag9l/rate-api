package com.rate.api.dto;

import com.rate.api.model.Degree;
import com.rate.api.model.Role;

public record LecturerRegisterRequest(
        String login,
        String password,
        Role role,
        String fullName,
        Degree degree
        ) {
}
