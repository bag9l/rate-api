package com.rate.api.repository;

import com.rate.api.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FacultyRepository extends JpaRepository<Faculty, String> {

    @Query("SELECT f FROM Faculty f " +
            "JOIN f.streams s " +
            "JOIN s.groups g " +
            "JOIN g.students student " +
            "WHERE student.login =:studentLogin")
    Optional<Faculty> findFacultyForStudentByLogin(@Param("studentLogin")String studentLogin);

    @Query("SELECT f FROM Faculty f " +
            "JOIN f.departments d " +
            "JOIN d.lecturers l " +
            "WHERE l.login =:lecturerLogin")
    Optional<Faculty> findFacultyForLecturerByLogin(@Param("lecturerLogin")String lecturerLogin);

    @Query("SELECT f FROM Faculty f " +
            "JOIN f.admin a " +
            "WHERE a.login =:adminLogin")
    Optional<Faculty> findFacultyForAdminByLogin(@Param("adminLogin")String adminLogin);
}
