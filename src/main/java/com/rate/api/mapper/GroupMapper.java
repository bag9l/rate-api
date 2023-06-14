package com.rate.api.mapper;

import com.rate.api.dto.GroupDto;
import com.rate.api.model.Group;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@RequiredArgsConstructor
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class GroupMapper {

    public abstract GroupDto groupToDto(Group group);
}
