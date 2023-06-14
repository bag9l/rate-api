package com.rate.api.service.impl;

import com.rate.api.dto.SubjectDto;
import com.rate.api.exception.EntityNotExistsException;
import com.rate.api.mapper.SubjectMapper;
import com.rate.api.model.Lecturer;
import com.rate.api.model.Student;
import com.rate.api.model.Subject;
import com.rate.api.model.User;
import com.rate.api.repository.LecturerRepository;
import com.rate.api.repository.StudentRepository;
import com.rate.api.repository.SubjectRepository;
import com.rate.api.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final LecturerRepository lecturerRepository;
    private final StudentRepository studentRepository;
    private final SubjectMapper subjectMapper;

    @Override
    public Subject getSubjectById(String id) {
        return subjectRepository.findById(id).orElseThrow(() ->
                new EntityNotExistsException("Subject with id:" + id + " not found"));
    }

    @Override
    public List<SubjectDto> getSubjectsByUserLogin(String login) {

        Student student = studentRepository.findStudentByLogin(login).orElse(null);

        Lecturer lecturer = lecturerRepository.findLecturerByLogin(login).orElse(null);

        if (student != null) {
            return studentRepository.findStudentSubjectsByLogin(login).stream()
                    .map(subjectMapper::subjectToDto)
                    .collect(Collectors.toList());
        } else if (lecturer != null) {
            return lecturerRepository.findLecturerSubjectsByLogin(login).stream()
                    .map(subjectMapper::subjectToDto)
                    .collect(Collectors.toList());
        } else {
            throw new EntityNotExistsException("User with login:" + login + " not found");
        }
    }
}
