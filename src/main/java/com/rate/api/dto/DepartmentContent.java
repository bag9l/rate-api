package com.rate.api.dto;

import java.util.List;

public record DepartmentContent(
        String id,
        String name,
        List<LecturerDto> lecturers
) {
}
