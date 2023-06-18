package com.rate.api.dto.auth.register;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record StudentRegisterRequest(
        @Size(min = 6, max = 20, message = "login shouldn't be shorter than 6 and longer than 20")
        @NotBlank(message = "login shouldn't be empty")
        String login,
        @Size(min = 8, max = 20, message = "password shouldn't be shorter than 8 and longer than 20")
        @NotBlank(message = "password shouldn't be empty")
        String password,
        @Size(min = 10, max = 100, message = "full name shouldn't be shorter than 10 and longer than 100")
        @NotBlank(message = "full name shouldn't be empty")
        String fullName,
        @Email(message = "email is not valid")
        String email,
        @NotNull(message = "group shouldn't be null")
        String groupId
) {
}
