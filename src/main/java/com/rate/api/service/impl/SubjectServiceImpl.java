package com.rate.api.service.impl;

import com.rate.api.dto.NewEducationalMethod;
import com.rate.api.dto.NewSubject;
import com.rate.api.dto.SubjectDto;
import com.rate.api.exception.EntityNotExistsException;
import com.rate.api.mapper.SubjectMapper;
import com.rate.api.model.*;
import com.rate.api.model.user.Lecturer;
import com.rate.api.model.user.Student;
import com.rate.api.repository.*;
import com.rate.api.service.EducationalMethodService;
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
    private final StreamRepository streamRepository;

    private final SubjectMapper subjectMapper;

    private final EducationalMethodService educationalMethodService;

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

    @Override
    public void addEducationalMethodToSubject(String subjectId, NewEducationalMethod newEducationalMethod) {
        educationalMethodService.addEducationalMethodToSubject(subjectId, newEducationalMethod);
    }

    @Override
    public String addSubjectForLecturer(String lecturerLogin, NewSubject newSubject) {
        Lecturer lecturer = lecturerRepository.findLecturerByLogin(lecturerLogin).orElseThrow(() ->
                new EntityNotExistsException("Lecturer with login:" + lecturerLogin + " not found"));
        Stream stream = streamRepository.findById(newSubject.streamId()).orElseThrow(() ->
                new EntityNotExistsException("Subject with id:" + newSubject.streamId() + " not found"));

        Subject subject = new Subject(newSubject.name(),
                lecturer,
                null,
                stream);

        subject = subjectRepository.save(subject);

        return subject.getId();
    }
}
