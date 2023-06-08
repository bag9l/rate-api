package com.rate.api.service;

import com.rate.api.dto.SubjectDto;

import java.util.List;

public interface StudentService {
    List<SubjectDto> getSubjectsByLogin(String login);
}
