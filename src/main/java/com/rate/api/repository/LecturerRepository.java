package com.rate.api.repository;

import com.rate.api.model.Lecturer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LecturerRepository extends UserRepository{
    @Query("SELECT l FROM Lecturer l WHERE l.login =:login")
    Optional<Lecturer> findByLogin(@Param("login") String login);
}
