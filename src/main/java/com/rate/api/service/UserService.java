package com.rate.api.service;

import com.rate.api.dto.UserProfile;
import com.rate.api.dto.UserUpdateData;
import com.rate.api.model.user.User;

import java.io.IOException;

public interface UserService {
    User findUserById(String id);

    UserProfile getUserProfileById(String id);

    void updateUser(UserUpdateData userUpdateData, String userId, String authenticatedUserLogin) throws IOException;

    User findUserByLogin(String login);
}
