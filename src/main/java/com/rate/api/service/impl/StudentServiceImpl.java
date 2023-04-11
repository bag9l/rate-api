package com.rate.api.service.impl;

import com.rate.api.model.Subject;
import com.rate.api.repository.StudentRepository;
import com.rate.api.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public List<Subject> getSubjectsByLogin(String login){
        return studentRepository.findStudentSubjectsByLogin(login);
    }
}
