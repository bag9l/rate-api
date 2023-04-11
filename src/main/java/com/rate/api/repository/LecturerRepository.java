package com.rate.api.repository;

import com.rate.api.model.Department;
import com.rate.api.model.Lecturer;
import com.rate.api.model.Subject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LecturerRepository extends UserRepository {
    @Query("SELECT l FROM Lecturer l WHERE l.login =:login")
    Optional<Lecturer> findByLogin(@Param("login") String login);

    @Query("SELECT subjects FROM Lecturer lecturer " +
            "JOIN lecturer.subjects subjects " +
            "WHERE lecturer.login =:login")
    List<Subject> findLecturerSubjectsByLogin(@Param("login") String login);

    @Query("SELECT d FROM Faculty f " +
            "JOIN f.departments d " +
            "WHERE f.id =:id")
    List<Department> findDepartmentsByFacultyId(@Param("id") String facultyId);
}
