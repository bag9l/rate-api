package com.rate.api.dto;

public record PasswordUpdate(
        String oldPassword,
        String newPassword
) {
}
