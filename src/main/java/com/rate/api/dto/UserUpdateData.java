package com.rate.api.dto;

import org.springframework.web.multipart.MultipartFile;

public record UserUpdateData(
        MultipartFile image
) {
}
