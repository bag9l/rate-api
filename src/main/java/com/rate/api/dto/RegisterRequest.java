package com.rate.api.dto;

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
