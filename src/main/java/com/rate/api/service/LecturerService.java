package com.rate.api.service;

import com.rate.api.model.Lecturer;
import com.rate.api.model.Subject;

import java.util.List;

public interface LecturerService {
    List<Subject> getSubjectsByLogin(String login);

    Lecturer getLecturerById(String id);

    List<Lecturer> getLectors(String login);
}
