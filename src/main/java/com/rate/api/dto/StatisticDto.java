package com.rate.api.dto;

public record StatisticDto(
        String id,
        UserView owner,
        Double qualityOfTeaching,
        Double methodologicalSupport,
        Double objectivityOfAssessment,
        String comment
) {
}
