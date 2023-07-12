package com.rate.api.dto;

import java.time.LocalDate;

public record FeedbackDto(
        String id,
        UserView owner,
        Double qualityOfTeaching,
        Double methodologicalSupport,
        Double objectivityOfAssessment,
        String comment,
        LocalDate date
) {
}
