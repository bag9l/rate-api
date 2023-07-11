package com.rate.api.dto.auth.register;

import com.rate.api.dto.DepartmentDto;

import java.util.List;

public record LecturerRegisterData(
        List<String> degrees,
        List<DepartmentDto> departments
) {
}
