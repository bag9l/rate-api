package com.rate.api.repository;

import com.rate.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UserRepository extends JpaRepository<User, String> {
}
