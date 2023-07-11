package com.rate.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewSubject(
        @Size(min = 5, max = 50, message = "comment should not be shorter than 5 and longer than 50")
        @NotBlank(message = "name shouldn't be empty")
        String name,
        @NotNull(message = "stream shouldn't be null")
        String streamId
) {
}
