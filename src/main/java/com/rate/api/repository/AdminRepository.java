package com.rate.api.repository;

import com.rate.api.model.Admin;
import com.rate.api.model.Department;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends UserRepository{
    Optional<Admin> findAdminByLogin(String login);

    @Query("SELECT d FROM Faculty f " +
            "JOIN f.departments d " +
            "WHERE f.id =:id")
    List<Department> findDepartmentsByFacultyId(@Param("id") String facultyId);

}
