package com.rate.api.service.impl;

import com.rate.api.exception.EntityNotExistsException;
import com.rate.api.model.Lecturer;
import com.rate.api.model.Student;
import com.rate.api.model.Subject;
import com.rate.api.repository.LecturerRepository;
import com.rate.api.repository.StudentRepository;
import com.rate.api.repository.SubjectRepository;
import com.rate.api.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final LecturerRepository lecturerRepository;
    private final StudentRepository studentRepository;

    @Override
    public Subject getSubjectById(String id) {
        return subjectRepository.findById(id).orElseThrow(() ->
                new EntityNotExistsException("Subject with id:" + id + " not found"));
    }

    @Override
    public List<Subject> getSubjectsByLogin(String login) {
        Student student = studentRepository.findStudentByLogin(login).orElse(null);

        Lecturer lecturer = lecturerRepository.findLecturerByLogin(login).orElse(null);

        if (student != null) {
            return lecturerRepository.findLecturerSubjectsByLogin(login);
        } else if (lecturer != null) {
            return studentRepository.findStudentSubjectsByLogin(login);
        } else {
            throw new EntityNotExistsException("User with login:" + login + " not found");
        }
    }
}
