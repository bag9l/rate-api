package com.rate.api.mapper;

import com.rate.api.dto.DepartmentContent;
import com.rate.api.dto.DepartmentDto;
import com.rate.api.dto.LecturerDto;
import com.rate.api.model.Department;
import lombok.RequiredArgsConstructor;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = UserMapper.class)
public abstract class DepartmentMapper {

    private UserMapper userMapper;

    public abstract DepartmentDto departmentToDto(Department department);

    public DepartmentContent departmentToDepartmentContent(Department department) {
        List<LecturerDto> lecturerDtos = department.getLecturers().stream()
                .map(lecturer -> userMapper.lecturerToDto(lecturer))
                .collect(Collectors.toList());

        return new DepartmentContent(department.getId(), department.getName(), lecturerDtos);
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper){
        this.userMapper = userMapper;
    }
}
