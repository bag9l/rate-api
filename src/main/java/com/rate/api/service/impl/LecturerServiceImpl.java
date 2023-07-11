package com.rate.api.service.impl;

import com.rate.api.dto.LecturerProfile;
import com.rate.api.dto.SubjectDto;
import com.rate.api.dto.UserUpdateData;
import com.rate.api.exception.EntityNotExistsException;
import com.rate.api.mapper.SubjectMapper;
import com.rate.api.mapper.UserMapper;
import com.rate.api.model.Avatar;
import com.rate.api.model.user.Lecturer;
import com.rate.api.model.user.Student;
import com.rate.api.repository.LecturerRepository;
import com.rate.api.repository.StudentRepository;
import com.rate.api.service.LecturerService;
import com.rate.api.util.ImageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LecturerServiceImpl implements LecturerService {

    private final LecturerRepository lecturerRepository;

    private final StudentRepository studentRepository;

    private final SubjectMapper subjectMapper;
    private final UserMapper userMapper;

    @Override
    public List<SubjectDto> getSubjectsByLogin(String login) {
        return lecturerRepository.findLecturerSubjectsByLogin(login).stream()
                .map(subjectMapper::subjectToDto)
                .collect(Collectors.toList());
    }

    @Override
    public LecturerProfile getLecturerProfileById(String id) {
        Lecturer lecturer = lecturerRepository.findLecturerById(id).orElseThrow(() ->
                new EntityNotExistsException("Lecturer with id:" + id + " not found"));
        System.out.println(lecturer);
        return userMapper.lecturerToLecturerProfile(lecturer);
    }

    @Override
    public List<Lecturer> getLecturersOfAuthenticatedUserFacultyByLogin(String login) {
        Student student = studentRepository.findStudentByLogin(login).orElse(null);

        Lecturer lecturer = lecturerRepository.findLecturerByLogin(login).orElse(null);

        if (student != null) {
            return lecturerRepository.findLectorsByFacultyId(student.getGroup().getStream().getCourse().getFaculty().getId());
        } else if (lecturer != null) {
            return lecturerRepository.findLectorsByFacultyId(lecturer.getDepartment().getFaculty().getId());
        } else {
            throw new EntityNotExistsException("User with login:" + login + " not found");
        }
    }
}
