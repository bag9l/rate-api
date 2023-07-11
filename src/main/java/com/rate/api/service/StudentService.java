package com.rate.api.service;

import com.rate.api.dto.StudentProfile;
import com.rate.api.dto.SubjectDto;
import com.rate.api.dto.UpdateStudentData;

import java.io.IOException;
import java.util.List;

public interface StudentService {
    List<SubjectDto> getSubjectsByLogin(String login);

    StudentProfile getStudentProfileById(String id);

    void updateStudent(UpdateStudentData updateStudentData, String studentId) throws IOException;
}
