package com.rate.api.service;

import com.rate.api.dto.NewEducationalMethod;
import com.rate.api.model.EducationalMethodType;

import java.util.List;

public interface EducationalMethodService {
    List<EducationalMethodType> getEducationalMethodTypes();

    void addEducationalMethodToSubject(String subjectId, NewEducationalMethod newEducationalMethod);
}
