package com.rate.api.service;

import com.rate.api.dto.PasswordUpdate;
import com.rate.api.dto.UserProfile;
import com.rate.api.dto.UserUpdateData;
import com.rate.api.dto.auth.AuthenticationResponse;
import com.rate.api.model.user.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;

public interface UserService {
    User findUserById(String id);

    UserProfile getUserProfileById(String id);

    void updateUser(UserUpdateData userUpdateData, String userId, String authenticatedUserLogin) throws IOException;

    AuthenticationResponse updatePassword(UserDetails userDetails, PasswordUpdate passwordUpdate);

    User findUserByLogin(String login);
}
