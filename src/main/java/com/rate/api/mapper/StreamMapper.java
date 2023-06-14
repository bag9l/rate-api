package com.rate.api.mapper;

import com.rate.api.dto.GroupDto;
import com.rate.api.dto.StreamDto;
import com.rate.api.model.Stream;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = GroupMapper.class)
public abstract class StreamMapper {

    private GroupMapper groupMapper;

    public StreamDto streamToDto(Stream stream) {
        List<GroupDto> groupDtos = stream.getGroups().stream()
                .map(groupMapper::groupToDto)
                .collect(Collectors.toList());

        return new StreamDto(stream.getId(), stream.getName(), groupDtos);
    }

    @Autowired
    public void setGroupMapper(GroupMapper groupMapper) {
        this.groupMapper = groupMapper;
    }
}
