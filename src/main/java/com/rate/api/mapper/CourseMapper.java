package com.rate.api.mapper;

import com.rate.api.dto.CourseDto;
import com.rate.api.dto.StreamDto;
import com.rate.api.model.Course;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = StreamMapper.class)
public abstract class CourseMapper {

    private StreamMapper streamMapper;

    public CourseDto courseToDto(Course course) {
        List<StreamDto> streamDtos = course.getStreams().stream()
                .map(streamMapper::streamToDto)
                .collect(Collectors.toList());

        return new CourseDto(course.getId(), course.getCourseNumber(), course.getDegree().name(), streamDtos);
    }

    @Autowired
    public void setStreamMapper(StreamMapper streamMapper) {
        this.streamMapper = streamMapper;
    }
}
