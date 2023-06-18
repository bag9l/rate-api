package com.rate.api.dto;

import jakarta.validation.constraints.*;

public record NewStatistic(
        @Min(value = 0, message = "quality of teaching should not be smaller than 0")
        @Max(value = 10, message = "quality of teaching should not be bigger than 10")
        @NotNull(message = "quality of teaching should not be null")
        Double qualityOfTeaching,
        @Min(value = 0, message = "methodological support should not be smaller than 0")
        @Max(value = 10, message = "methodological support should not be bigger than 10")
        @NotNull(message = "methodological support should not be null")
        Double methodologicalSupport,
        @Min(value = 0, message = "objectivity of assessment should not be smaller than 0")
        @Max(value = 10, message = "objectivity of assessment should not be bigger than 10")
        @NotNull(message = "objectivity of assessment should not be null")
        Double objectivityOfAssessment,
        @Size(min=5, max = 1000, message = "comment should not be shorter than 5 and longer than 1000")
        @NotBlank(message = "comment shouldn't be empty")
        String comment
) {
}
