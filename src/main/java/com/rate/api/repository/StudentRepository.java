package com.rate.api.repository;

import com.rate.api.model.user.Student;
import com.rate.api.model.Subject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends UserRepository {
    //    @Query("SELECT s FROM Student s WHERE s.login =:login")
    Optional<Student> findStudentByLogin(String login);
    Optional<Student> findStudentById(String id);

    @Query("SELECT subjects FROM Student student " +
            "JOIN student.group g JOIN g.stream stream JOIN stream.subjects subjects " +
            "WHERE student.login =:login")
    List<Subject> findStudentSubjectsByLogin(@Param("login") String login);

}
