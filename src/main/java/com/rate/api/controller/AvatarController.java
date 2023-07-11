package com.rate.api.controller;

import com.rate.api.service.AvatarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("images")
@RestController
public class AvatarController {

    private final AvatarService avatarService;

//    @PostMapping
//    public ResponseEntity<Void> uploadImage(@RequestParam("image")MultipartFile file) throws IOException {
//        avatarService.uploadAvatar(file);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }

    @GetMapping("/{id}")
    private ResponseEntity<?> downloadImage(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(MediaType.IMAGE_PNG_VALUE))
                .body(avatarService.getUserAvatar(id));
    }
}
