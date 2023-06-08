package com.rate.api.mapper;

import com.rate.api.dto.DepartmentDto;
import com.rate.api.model.Department;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DepartmentMapper {

    DepartmentDto departmentToDto(Department department);
}
