package com.rate.api.mapper;

import com.rate.api.dto.SubjectDto;
import com.rate.api.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SubjectMapper {

    SubjectDto subjectToDto(Subject subject);
}
