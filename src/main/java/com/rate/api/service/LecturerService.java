package com.rate.api.service;

import com.rate.api.dto.LecturerProfile;
import com.rate.api.dto.SubjectDto;
import com.rate.api.dto.UpdateLecturerData;
import com.rate.api.model.user.Lecturer;

import java.io.IOException;
import java.util.List;

public interface LecturerService {
    List<SubjectDto> getSubjectsByLogin(String login);

    LecturerProfile getLecturerProfileById(String id);

    List<Lecturer> getLecturersOfAuthenticatedUserFacultyByLogin(String login);

    void updateLecturer(UpdateLecturerData updateLecturerData, String lecturerId) throws IOException;
}
