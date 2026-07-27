package com.airecruitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.airecruitment.dto.InterviewRequestDTO;
import com.airecruitment.entity.Interview;
import com.airecruitment.service.InterviewService;

@RestController
@RequestMapping("/api/interviews")
@CrossOrigin(origins = "*")
public class InterviewController {

    @Autowired
    private InterviewService interviewService;

    @PostMapping("/schedule")
    public ResponseEntity<?> scheduleInterview(@RequestBody InterviewRequestDTO request) {

        System.out.println("========== Interview API Called ==========");
        System.out.println("Candidate ID : " + request.getCandidateId());
        System.out.println("Job ID       : " + request.getJobId());
        System.out.println("Date         : " + request.getInterviewDate());
        System.out.println("Time         : " + request.getInterviewTime());

        Interview interview = interviewService.scheduleInterview(request);

        return ResponseEntity.ok(interview);
    }
    @GetMapping
    public ResponseEntity<?> getAllInterviews() {
        return ResponseEntity.ok(interviewService.getAllInterviews());
    }
}