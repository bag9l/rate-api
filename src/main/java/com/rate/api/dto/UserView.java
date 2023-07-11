package com.rate.api.dto;

public record UserView(
        String id,
        byte[] avatar,
        String fullName
) {
}
