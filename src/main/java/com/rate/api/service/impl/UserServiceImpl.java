package com.rate.api.service.impl;

import com.rate.api.exception.EntityNotExistsException;
import com.rate.api.model.Lecturer;
import com.rate.api.model.Student;
import com.rate.api.model.User;
import com.rate.api.repository.LecturerRepository;
import com.rate.api.repository.StudentRepository;
import com.rate.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final LecturerRepository lecturerRepository;
    private final StudentRepository studentRepository;

    @Override
    public User getUserById(String id) {
        Student student = studentRepository.findStudentById(id).orElse(null);

        Lecturer lecturer = lecturerRepository.findLecturerById(id).orElse(null);

        if (student != null) {
            return student;
        } else if (lecturer != null) {
            return lecturer;
        } else {
            throw new EntityNotExistsException("User with id:" + id + " not found");
        }
    }
}
