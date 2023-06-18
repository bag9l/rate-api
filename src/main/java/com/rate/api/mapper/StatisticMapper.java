package com.rate.api.mapper;

import com.rate.api.dto.StatisticDto;
import com.rate.api.model.Statistic;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = UserMapper.class)
public abstract class StatisticMapper {

    protected UserMapper userMapper;

    @Mapping(target = "owner",
            expression = "java(userMapper.userToUserView(statistic.getOwner()))")
    public abstract StatisticDto statisticToDto(Statistic statistic);


    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
