package com.rate.api.mapper;

import com.rate.api.dto.auth.AuthenticatedUser;
import com.rate.api.dto.LecturerDto;
import com.rate.api.model.Lecturer;
import com.rate.api.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class UserMapper {

    @Mapping(target = "role", source = "role.value")
    public abstract AuthenticatedUser userToAuthenticatedUser(User user);

    @Mapping(target = "degree", source = "degree.value")
    public abstract LecturerDto lecturerToDto(Lecturer lecturer);
}
