package com.rate.api.service;

import com.rate.api.dto.NewStatistic;

public interface StatisticService {

    void addStatistic(NewStatistic newStatistic, String studentLogin, String lecturerId);
}
