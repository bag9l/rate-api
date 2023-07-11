package com.rate.api.service.impl;

import com.rate.api.model.Avatar;
import com.rate.api.model.user.User;
import com.rate.api.repository.AvatarRepository;
import com.rate.api.service.AvatarService;
import com.rate.api.service.UserService;
import com.rate.api.util.ImageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class AvatarServiceImpl implements AvatarService {

    private final AvatarRepository avatarRepository;
    private final UserService userService;


    @Override
    public void uploadAvatar(MultipartFile file, String userId) throws IOException {

        User user = userService.findUserById(userId);

        Avatar avatar = new Avatar(file.getOriginalFilename(),
                file.getContentType(),
                ImageUtil.compressImage(file.getBytes()),
                user);

        avatarRepository.save(avatar);
    }

    @Override
    public byte[] getUserAvatar(String userId) {
        User user = userService.findUserById(userId);

        return ImageUtil.decompressImage(user.getAvatar().getImageData());
    }
}
