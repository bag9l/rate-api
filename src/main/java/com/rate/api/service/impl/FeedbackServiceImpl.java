package com.rate.api.service.impl;

import com.rate.api.dto.NewFeedback;
import com.rate.api.exception.EntityNotExistsException;
import com.rate.api.model.Feedback;
import com.rate.api.model.user.Lecturer;
import com.rate.api.model.user.Student;
import com.rate.api.repository.LecturerRepository;
import com.rate.api.repository.FeedbackRepository;
import com.rate.api.repository.StudentRepository;
import com.rate.api.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final StudentRepository studentRepository;
    private final LecturerRepository lecturerRepository;

    @Override
    public void addFeedback(NewFeedback newFeedback, String studentLogin, String lecturerId) {
        Student student = studentRepository.findStudentByLogin(studentLogin).orElseThrow(() ->
                new EntityNotExistsException("Student with login:" + studentLogin + " not found"));
        Lecturer lecturer = lecturerRepository.findLecturerById(lecturerId).orElseThrow(() ->
                new EntityNotExistsException("Lecturer with id:" + lecturerId + " not found"));

        Feedback feedback = new Feedback(lecturer,
                student,
                newFeedback.qualityOfTeaching(),
                newFeedback.methodologicalSupport(),
                newFeedback.objectivityOfAssessment(),
                newFeedback.comment(),
                LocalDate.now());

        feedbackRepository.save(feedback);
    }
}