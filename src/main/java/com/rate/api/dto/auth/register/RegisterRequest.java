package com.rate.api.dto.auth.register;

import com.rate.api.model.Role;

public record RegisterRequest (
        String login,
        String password,
        Role role,
        String email,
        String fullName,
        Integer course,
        String groupId
){
}
