package com.rate.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LecturerProfile extends UserProfile {
    private String degree;
    private String department;
    private List<FeedbackDto> feedbacks;
}
