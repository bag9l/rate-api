package com.rate.api.mapper;

import com.rate.api.dto.SubjectDto;
import com.rate.api.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SubjectMapper {

    SubjectDto subjectToDto(Subject subject);
}
