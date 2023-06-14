package com.rate.api.repository;

import com.rate.api.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, String> {
    @Query("SELECT c FROM Faculty f " +
            "JOIN f.courses c " +
            "WHERE f.id =:id")
    List<Course> findAllCoursesForFacultyById(@Param("id") String facultyId);
}
