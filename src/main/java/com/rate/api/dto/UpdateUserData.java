package com.rate.api.dto;

import org.springframework.web.multipart.MultipartFile;

public record UpdateUserData(
        MultipartFile image
) {
}
