package com.rate.api.dto.auth.register;

import com.rate.api.dto.CourseDto;

import java.util.List;

public record StudentRegisterData(
        List<CourseDto> courses
) {
}
