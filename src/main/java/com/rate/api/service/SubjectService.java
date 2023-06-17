package com.rate.api.service;

import com.rate.api.dto.NewEducationalMethod;
import com.rate.api.dto.NewSubject;
import com.rate.api.dto.SubjectDto;
import com.rate.api.model.Subject;

import java.util.List;

public interface SubjectService {

    Subject getSubjectById(String id);

    List<SubjectDto> getSubjectsByUserLogin(String login);

    void addEducationalMethodToSubject(String subjectId, NewEducationalMethod educationalMethod);

    String addSubjectForLecturer(String lecturerLogin, NewSubject newSubject);
}
