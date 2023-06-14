package com.rate.api.service;

import com.rate.api.dto.SubjectDto;
import com.rate.api.model.Subject;

import java.util.List;

public interface SubjectService {

    Subject getSubjectById(String id);

    List<SubjectDto> getSubjectsByUserLogin(String login);
}
