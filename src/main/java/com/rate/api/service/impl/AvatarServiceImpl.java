package com.rate.api.service.impl;

import com.rate.api.exception.EntityNotExistsException;
import com.rate.api.model.Avatar;
import com.rate.api.model.User;
import com.rate.api.repository.AdminRepository;
import com.rate.api.repository.AvatarRepository;
import com.rate.api.repository.LecturerRepository;
import com.rate.api.repository.StudentRepository;
import com.rate.api.service.AvatarService;
import com.rate.api.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class AvatarServiceImpl implements AvatarService {

    private final AvatarRepository avatarRepository;
    private final StudentRepository studentRepository;
    private final LecturerRepository lecturerRepository;
    private final AdminRepository adminRepository;
    private final ImageService imageService;

    @Override
    public void uploadAvatar(MultipartFile file, String userId) throws IOException {

        User user = findUserById(userId);

        Avatar avatar = new Avatar(file.getOriginalFilename(),
                file.getContentType(),
                imageService.compressImage(file.getBytes()),
                user);

        avatarRepository.save(avatar);
    }

    @Override
    public byte[] downloadImage(String id) {
        Avatar avatar = avatarRepository.findById(id).orElseThrow(() ->
                new EntityNotExistsException("Avatar not found"));
        return imageService.decompressImage(avatar.getImageData());
    }


    private User findUserById(String id) {
        return studentRepository.findById(id).orElseGet(() ->
                lecturerRepository.findById(id).orElseGet(() ->
                        adminRepository.findById(id).orElseThrow(() ->
                                new EntityNotExistsException("User not found"))));
    }
}
