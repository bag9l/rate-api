package com.rate.api.dto;

import java.util.List;

public record StreamDto(
        String id,
        String name,
        List<GroupDto> groups
) {
}
