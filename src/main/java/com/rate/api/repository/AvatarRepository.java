package com.rate.api.repository;

import com.rate.api.model.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvatarRepository extends JpaRepository<Avatar, String> {
}
