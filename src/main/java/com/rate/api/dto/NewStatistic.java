package com.rate.api.dto;

public record NewStatistic(
        Double qualityOfTeaching,
        Double methodologicalSupport,
        Double objectivityOfAssessment,
        String comment
) {
}
