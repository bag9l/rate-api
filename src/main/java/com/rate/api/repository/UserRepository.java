package com.rate.api.repository;

import com.rate.api.model.user.User;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface UserRepository extends JpaRepository<User, String> {
}
