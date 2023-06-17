package com.rate.api.dto;

import com.rate.api.model.EducationalMethodType;

public record NewEducationalMethod(
        EducationalMethodType type,
        String name
) {
}
