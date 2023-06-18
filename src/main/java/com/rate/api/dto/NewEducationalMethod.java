package com.rate.api.dto;

import com.rate.api.model.EducationalMethodType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewEducationalMethod(
        @NotNull(message = "type shouldn't be null")
        EducationalMethodType type,
        @Size(min = 1, max = 50, message = "name shouldn't be shorter than 1 and longer than 50")
        @NotBlank(message = "name shouldn't be empty")
        String name
) {
}
