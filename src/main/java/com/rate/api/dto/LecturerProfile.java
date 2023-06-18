package com.rate.api.dto;

import java.util.List;

public record LecturerProfile (
        String fullName,
        String email,
        String degree,
        String department,
        List<StatisticDto> statistics
){
}
