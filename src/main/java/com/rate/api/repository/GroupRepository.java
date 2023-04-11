package com.rate.api.repository;

import com.rate.api.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, String> {
}
