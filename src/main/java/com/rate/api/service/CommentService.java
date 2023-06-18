package com.rate.api.service;

import com.rate.api.dto.NewComment;
import com.rate.api.model.EducationalMethod;
import com.rate.api.model.Student;

public interface CommentService {
    void addCommentToEducationalMethod(NewComment newComment, EducationalMethod educationalMethod, Student owner);

}
