package com.rate.api.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NewComment(
        @NotBlank(message = "comment shouldn't be empty")
        String text,
        @Min(value = 0, message = "score should not be smaller than 0")
        @Max(value = 5, message = "score should not be bigger than 5")
        @NotNull(message = "score should not be null")
        Double score
) {
}
