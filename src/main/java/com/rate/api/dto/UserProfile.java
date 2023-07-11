package com.rate.api.dto;

import com.rate.api.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {
    private String fullName;
    private String email;
    private Role role;
    private byte[] avatar;
    private String faculty;
}
