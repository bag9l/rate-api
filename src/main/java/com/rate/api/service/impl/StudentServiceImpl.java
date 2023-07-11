package com.rate.api.service.impl;

import com.rate.api.dto.StudentProfile;
import com.rate.api.dto.SubjectDto;
import com.rate.api.exception.EntityNotExistsException;
import com.rate.api.mapper.SubjectMapper;
import com.rate.api.mapper.UserMapper;
import com.rate.api.model.user.Student;
import com.rate.api.repository.StudentRepository;
import com.rate.api.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final SubjectMapper subjectMapper;
    private final UserMapper userMapper;


    @Override
    @Transactional
    public List<SubjectDto> getSubjectsByLogin(String login) {
        return studentRepository.findStudentSubjectsByLogin(login).stream()
                .map(subjectMapper::subjectToDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentProfile getStudentProfileById(String id) {
        Student student = studentRepository.findStudentById(id).orElseThrow(() ->
                new EntityNotExistsException("Lecturer with id:" + id + " not found"));
        System.out.println(student);
        return userMapper.studentToStudentProfile(student);
    }
}
