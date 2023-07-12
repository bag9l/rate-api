package com.rate.api.service;

import com.rate.api.dto.NewFeedback;

public interface FeedbackService {

    void addFeedback(NewFeedback newFeedback, String studentLogin, String lecturerId);
}
