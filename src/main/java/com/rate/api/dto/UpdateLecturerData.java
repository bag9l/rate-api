package com.rate.api.dto;

import org.springframework.web.multipart.MultipartFile;

public record UpdateLecturerData(
        MultipartFile image
) {
}
