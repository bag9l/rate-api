package com.rate.api.dto.auth.register;

import com.rate.api.model.Degree;
import com.rate.api.model.Role;

import java.util.Optional;

public record StudentRegisterRequest(
        String login,
        String password,
//        Role role,
        String email,
        String fullName,
        Integer course,
        String groupId
//        Degree degree,
//        String departmentId
) {

}
