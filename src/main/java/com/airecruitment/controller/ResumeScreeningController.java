package com.airecruitment.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.airecruitment.entity.Job;
import com.airecruitment.repository.JobRepository;
import com.airecruitment.service.ResumeScreeningService;

@RestController
@RequestMapping("/api/screen")
@CrossOrigin(origins = "*")
public class ResumeScreeningController {

    @Autowired
    private ResumeScreeningService resumeScreeningService;

    @Autowired
    private JobRepository jobRepository;

    @PostMapping
    public double screenResume(
            @RequestParam String resumePath,
            @RequestParam Long jobId) throws IOException {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        return resumeScreeningService.screenResume(
                resumePath,
                job.getSkillsRequired());
    }
}