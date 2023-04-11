package com.rate.api.service.impl;

import com.rate.api.model.Subject;
import com.rate.api.repository.LecturerRepository;
import com.rate.api.service.LecturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LecturerServiceImpl implements LecturerService {

    private final LecturerRepository lecturerRepository;

    @Override
    public List<Subject> getSubjectsByLogin(String login) {

        return lecturerRepository.findLecturerSubjectsByLogin(login);
    }
}
