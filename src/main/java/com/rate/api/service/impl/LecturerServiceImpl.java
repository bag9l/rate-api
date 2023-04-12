package com.rate.api.service.impl;

import com.rate.api.exception.EntityNotExistsException;
import com.rate.api.model.Lecturer;
import com.rate.api.model.Student;
import com.rate.api.model.Subject;
import com.rate.api.repository.LecturerRepository;
import com.rate.api.repository.StudentRepository;
import com.rate.api.service.LecturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LecturerServiceImpl implements LecturerService {

    private final LecturerRepository lecturerRepository;

    private final StudentRepository studentRepository;

    @Override
    public List<Subject> getSubjectsByLogin(String login) {

        return lecturerRepository.findLecturerSubjectsByLogin(login);
    }

    @Override
    public Lecturer getLecturerById(String id) {
        return lecturerRepository.findLecturerById(id).orElseThrow(() ->
                new EntityNotExistsException("Lecturer with id:" + id + " not found"));
    }

    @Override
    public List<Lecturer> getLectors(String login) {
        Student student = studentRepository.findStudentByLogin(login).orElse(null);

        Lecturer lecturer = lecturerRepository.findLecturerByLogin(login).orElse(null);

        if (student != null) {
            return lecturerRepository.findLectorsByFacultyId(student.getGroup().getStream().getFaculty().getId());
        } else if (lecturer != null) {
            return lecturerRepository.findLectorsByFacultyId(lecturer.getDepartment().getFaculty().getId());
        } else {
           throw new EntityNotExistsException("User with login:" + login + " not found");
        }
    }
}