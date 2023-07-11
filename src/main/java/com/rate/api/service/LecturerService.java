package com.rate.api.service;

import com.rate.api.dto.LecturerProfile;
import com.rate.api.dto.SubjectDto;
import com.rate.api.model.user.Lecturer;

import java.util.List;

public interface LecturerService {
    List<SubjectDto> getSubjectsByLogin(String login);

    LecturerProfile getLecturerProfileById(String id);

    List<Lecturer> getLecturersOfAuthenticatedUserFacultyByLogin(String login);
}
