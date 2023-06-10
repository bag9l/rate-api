package com.rate.api.mapper;

import com.rate.api.dto.AuthenticatedUser;
import com.rate.api.dto.LecturerDto;
import com.rate.api.model.Lecturer;
import com.rate.api.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(target = "role", source = "role.value")
    AuthenticatedUser userToAuthenticatedUser(User user);

    @Mapping(target = "degree", source = "degree.value")
    LecturerDto lecturerToDto(Lecturer lecturer);
}
