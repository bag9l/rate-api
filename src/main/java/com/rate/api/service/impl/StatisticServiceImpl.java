package com.rate.api.service.impl;

import com.rate.api.dto.NewStatistic;
import com.rate.api.exception.EntityNotExistsException;
import com.rate.api.model.user.Lecturer;
import com.rate.api.model.Statistic;
import com.rate.api.model.user.Student;
import com.rate.api.repository.LecturerRepository;
import com.rate.api.repository.StatisticRepository;
import com.rate.api.repository.StudentRepository;
import com.rate.api.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {

    private final StatisticRepository statisticRepository;
    private final StudentRepository studentRepository;
    private final LecturerRepository lecturerRepository;

    @Override
    public void addStatistic(NewStatistic newStatistic, String studentLogin, String lecturerId) {
        Student student = studentRepository.findStudentByLogin(studentLogin).orElseThrow(() ->
                new EntityNotExistsException("Student with login:" + studentLogin + " not found"));
        Lecturer lecturer = lecturerRepository.findLecturerById(lecturerId).orElseThrow(() ->
                new EntityNotExistsException("Lecturer with id:" + lecturerId + " not found"));

        Statistic statistic = new Statistic(lecturer,
                student,
                newStatistic.qualityOfTeaching(),
                newStatistic.methodologicalSupport(),
                newStatistic.objectivityOfAssessment(),
                newStatistic.comment());

        statisticRepository.save(statistic);
    }
}