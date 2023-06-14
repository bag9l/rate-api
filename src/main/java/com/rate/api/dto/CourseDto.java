package com.rate.api.dto;

import java.util.List;

public record CourseDto(
        String id,
        Integer courseNumber,
        String degree,
        List<StreamDto> streams
) {
}
