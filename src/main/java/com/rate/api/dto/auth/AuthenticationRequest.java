package com.rate.api.dto.auth;

public record AuthenticationRequest(
        String login,
        String password
) {
}
