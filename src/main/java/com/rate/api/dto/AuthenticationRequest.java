package com.rate.api.dto;

public record AuthenticationRequest (
        String login,
        String password
){
}
