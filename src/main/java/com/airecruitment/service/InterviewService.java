package com.airecruitment.service;

import java.util.List;

import com.airecruitment.dto.InterviewRequestDTO;
import com.airecruitment.entity.Interview;

public interface InterviewService {

    Interview scheduleInterview(InterviewRequestDTO request);

    
    List<Interview> getAllInterviews();
}