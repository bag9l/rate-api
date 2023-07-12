package com.rate.api.mapper;

import com.rate.api.dto.FeedbackDto;
import com.rate.api.model.Feedback;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = UserMapper.class)
public abstract class FeedbackMapper {

    protected UserMapper userMapper;

    @Mapping(target = "owner",
            expression = "java(userMapper.userToUserView(feedback.getOwner()))")
    public abstract FeedbackDto feedbackToDto(Feedback feedback);


    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
