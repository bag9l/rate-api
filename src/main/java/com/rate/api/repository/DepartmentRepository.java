package com.rate.api.repository;

import com.rate.api.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, String> {

    @Query("SELECT d FROM Faculty f " +
            "JOIN f.departments d " +
            "WHERE f.id =:facultyId")
    List<Department> findDepartmentsByFacultyId(@Param("facultyId") String facultyId);
}
