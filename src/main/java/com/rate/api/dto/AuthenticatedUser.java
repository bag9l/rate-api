package com.rate.api.dto;

public record AuthenticatedUser(
        String login,
        String fullName,
        String email,
        String role
) {
}
