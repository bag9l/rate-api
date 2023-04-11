package com.rate.api.repository;

import com.rate.api.model.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticRepository extends JpaRepository<Statistic, String> {
}
