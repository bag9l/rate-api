package com.rate.api.repository;

import com.rate.api.model.Stream;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StreamRepository extends JpaRepository<Stream, String> {
}
