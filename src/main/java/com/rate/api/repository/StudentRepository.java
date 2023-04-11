package com.rate.api.repository;

import com.rate.api.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentRepository extends UserRepository{
    @Query("SELECT s FROM Student s WHERE s.login =:login")
    Optional<Student> findByLogin(@Param("login") String login);

}
