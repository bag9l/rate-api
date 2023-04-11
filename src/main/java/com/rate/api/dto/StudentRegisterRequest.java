package com.rate.api.dto;

import com.rate.api.model.Role;

public record StudentRegisterRequest(
        String login,
        String password,
        Role role,
        String fullName,
        String group
) {
}
