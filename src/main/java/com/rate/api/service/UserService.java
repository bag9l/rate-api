package com.rate.api.service;

import com.rate.api.dto.UserProfile;
import com.rate.api.model.user.User;

public interface UserService {
    User findUserById(String id);

    UserProfile getUserProfileById(String id);

    User findUserByLogin(String login);
}
