package com.rate.api.repository;

import com.rate.api.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, String> {
}
