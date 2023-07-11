package com.rate.api.service.impl;

import com.rate.api.dto.UserProfile;
import com.rate.api.dto.UserUpdateData;
import com.rate.api.exception.EntityNotExistsException;
import com.rate.api.exception.PermissionException;
import com.rate.api.mapper.UserMapper;
import com.rate.api.model.Avatar;
import com.rate.api.model.user.Admin;
import com.rate.api.model.user.Lecturer;
import com.rate.api.model.user.Student;
import com.rate.api.model.user.User;
import com.rate.api.repository.AdminRepository;
import com.rate.api.repository.LecturerRepository;
import com.rate.api.repository.StudentRepository;
import com.rate.api.service.UserService;
import com.rate.api.util.ImageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final LecturerRepository lecturerRepository;
    private final StudentRepository studentRepository;
    private final AdminRepository adminRepository;
    private final UserMapper userMapper;

    @Override
    public User findUserById(String id) {
        return studentRepository.findById(id).orElseGet(() ->
                lecturerRepository.findById(id).orElseGet(() ->
                        adminRepository.findById(id).orElseThrow(() ->
                                new EntityNotExistsException("User with id: " + id + " not found"))));
    }

    @Override
    public UserProfile getUserProfileById(String id) {
        User user = findUserById(id);
        UserProfile userProfile = null;

        if (user == null) {
            throw new EntityNotExistsException("User with id: " + id + " not found");
        }

        switch (user.getRole()) {
            case STUDENT -> userProfile = userMapper.studentToStudentProfile((Student) user);
            case LECTURER -> userProfile = userMapper.lecturerToLecturerProfile((Lecturer) user);
        }
        return userProfile;
    }

    @Override
    public void updateUser(UserUpdateData userUpdateData, String userId, String authenticatedUserLogin) throws IOException {
        User user = studentRepository.findById(userId).orElseGet(() ->
                lecturerRepository.findById(userId).orElseThrow(() ->
                        new EntityNotExistsException("User with id:" + userId + " not found")));

        if (!user.getLogin().equals(authenticatedUserLogin)) {
            throw new PermissionException("You cannot change the data of other users");
        }

        MultipartFile file = userUpdateData.image();

        Avatar avatar = new Avatar(file.getOriginalFilename(),
                file.getContentType(),
                ImageUtil.compressImage(file.getBytes()),
                user);

        user.setAvatar(avatar);

        switch (user.getRole()) {
            case STUDENT -> studentRepository.save(user);
            case LECTURER -> lecturerRepository.save(user);
        }
    }

    @Override
    public User findUserByLogin(String login) {
        Optional<Student> student = studentRepository.findStudentByLogin(login);
        Optional<Lecturer> lecturer = lecturerRepository.findLecturerByLogin(login);
        Optional<Admin> admin = adminRepository.findAdminByLogin(login);

        if (student.isPresent()) {
            return student.get();
        } else if (lecturer.isPresent()) {
            return lecturer.get();
        } else if (admin.isPresent()) {
            return admin.get();
        } else throw new EntityNotExistsException("User with username: " + login + " not found");
    }
}
