package com.rate.api.repository;

import com.rate.api.model.Department;
import com.rate.api.model.Subject;
import com.rate.api.model.user.Lecturer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LecturerRepository extends UserRepository {
    //    @Query("SELECT l FROM Lecturer l WHERE l.login =:login")
    Optional<Lecturer> findLecturerByLogin(String login);

    Optional<Lecturer> findLecturerById(String id);

    @Query("SELECT subjects FROM Lecturer lecturer " +
            "JOIN lecturer.subjects subjects " +
            "WHERE lecturer.login =:login")
    List<Subject> findLecturerSubjectsByLogin(@Param("login") String login);

    @Query("SELECT d FROM Faculty f " +
            "JOIN f.departments d " +
            "WHERE f.id =:id")
    List<Department> findDepartmentsByFacultyId(@Param("id") String facultyId);

    @Query("SELECT l FROM Faculty f " +
            "JOIN f.departments d " +
            "JOIN d.lecturers l " +
            "WHERE f.id =:id")
    List<Lecturer> findLectorsByFacultyId(@Param("id") String facultyId);
}
