package com.rate.api.service.impl;

import com.rate.api.dto.NewComment;
import com.rate.api.dto.NewEducationalMethod;
import com.rate.api.exception.EntityNotExistsException;
import com.rate.api.model.EducationalMethod;
import com.rate.api.model.EducationalMethodType;
import com.rate.api.model.Subject;
import com.rate.api.model.user.Student;
import com.rate.api.repository.EducationalMethodRepository;
import com.rate.api.repository.StudentRepository;
import com.rate.api.repository.SubjectRepository;
import com.rate.api.service.CommentService;
import com.rate.api.service.EducationalMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EducationalMethodServiceImpl implements EducationalMethodService {

    private final EducationalMethodRepository educationalMethodRepository;
    private final SubjectRepository subjectRepository;
    private final StudentRepository studentRepository;

    private final CommentService commentService;


    @Override
    public List<EducationalMethodType> getEducationalMethodTypes() {
        return List.of(EducationalMethodType.values());
    }

    @Override
    public void addEducationalMethodToSubject(String subjectId, NewEducationalMethod newEducationalMethod) {
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() ->
                new EntityNotExistsException("Subject with id:" + subjectId + " not found"));

        EducationalMethod educationalMethod = new EducationalMethod(
                newEducationalMethod.name(),
                newEducationalMethod.type(),
                subject,
                null);

        educationalMethodRepository.save(educationalMethod);
    }

    @Override
    public void addCommentToEducationalMethod(String educationalMethodId, NewComment newComment, String studentLogin) {
        EducationalMethod educationalMethod = educationalMethodRepository.findById(educationalMethodId).orElseThrow(() ->
                new EntityNotExistsException("Educational method with id:" + educationalMethodId + " not found"));
        Student student = studentRepository.findStudentByLogin(studentLogin).orElseThrow(() ->
                new EntityNotExistsException("Student with login:" + studentLogin + " not found"));

        commentService.addCommentToEducationalMethod(newComment, educationalMethod, student);
    }
}
