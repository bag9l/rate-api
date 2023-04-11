package com.rate.api.repository;

import com.rate.api.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, String> {
}
