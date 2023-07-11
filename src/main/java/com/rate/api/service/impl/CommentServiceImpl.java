package com.rate.api.service.impl;

import com.rate.api.dto.NewComment;
import com.rate.api.model.Comment;
import com.rate.api.model.EducationalMethod;
import com.rate.api.model.user.Student;
import com.rate.api.repository.CommentRepository;
import com.rate.api.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;


    @Override
    public void addCommentToEducationalMethod(NewComment newComment, EducationalMethod educationalMethod, Student owner) {
        Comment comment = new Comment(
                owner,
                newComment.text(),
                LocalDate.now(),
                newComment.score(),
                educationalMethod);

        commentRepository.save(comment);
    }
}
