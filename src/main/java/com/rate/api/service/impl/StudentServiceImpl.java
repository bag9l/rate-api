package com.rate.api.service.impl;

import com.rate.api.dto.StudentProfile;
import com.rate.api.dto.SubjectDto;
import com.rate.api.dto.UpdateStudentData;
import com.rate.api.exception.EntityNotExistsException;
import com.rate.api.mapper.SubjectMapper;
import com.rate.api.mapper.UserMapper;
import com.rate.api.model.Avatar;
import com.rate.api.model.user.Student;
import com.rate.api.repository.StudentRepository;
import com.rate.api.service.StudentService;
import com.rate.api.util.ImageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @Override
    public void updateStudent(UpdateStudentData updateStudentData, String studentId) throws IOException {
        Student student = studentRepository.findStudentById(studentId).orElseThrow(() ->
                new EntityNotExistsException("Student with id:" + studentId + " not found"));

        MultipartFile file = updateStudentData.image();

        Avatar avatar = new Avatar(file.getOriginalFilename(),
                file.getContentType(),
                ImageUtil.compressImage(file.getBytes()),
                student);

        student.setAvatar(avatar);

        studentRepository.save(student);
    }
}
