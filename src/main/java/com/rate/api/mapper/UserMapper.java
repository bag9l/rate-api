package com.rate.api.mapper;

import com.rate.api.dto.AuthenticatedUser;
import com.rate.api.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(target = "role", source = "role.value")
    AuthenticatedUser userToAuthenticatedUser(User user);
}
