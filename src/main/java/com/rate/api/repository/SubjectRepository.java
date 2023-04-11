package com.rate.api.repository;

import com.rate.api.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, String> {
}
