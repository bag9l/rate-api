package com.rate.api.service;

import com.rate.api.model.Subject;

import java.util.List;

public interface StudentService {
    List<Subject> getSubjectsByLogin(String login);
}
