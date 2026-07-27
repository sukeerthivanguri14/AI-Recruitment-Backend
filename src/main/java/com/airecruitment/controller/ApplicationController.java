package com.airecruitment.controller;

import com.airecruitment.dto.MyApplicationDTO;
import com.airecruitment.dto.RecruiterJobDTO;
import com.airecruitment.entity.Application;
import com.airecruitment.service.ApplicationService;
import org.springframework.web.bind.annotation.*;
import com.airecruitment.dto.ApplicantDTO;
import java.util.List;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "*")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    public Application applyJob(@RequestBody Application application) {
        return applicationService.applyJob(application);
    }

    @GetMapping
    public List<Application> getAllApplications() {
        return applicationService.getAllApplications();
    }

    @GetMapping("/candidate/{candidateId}")
    public List<Application> getApplicationsByCandidate(
            @PathVariable Long candidateId) {

        return applicationService.getApplicationsByCandidate(candidateId);
    }

    @GetMapping("/job/{jobId}")
    public List<Application> getApplicationsByJob(
            @PathVariable Long jobId) {

        return applicationService.getApplicationsByJob(jobId);
    }

    @GetMapping("/candidate/{candidateId}/details")
    public List<MyApplicationDTO> getMyApplications(
            @PathVariable Long candidateId) {

        return applicationService.getMyApplications(candidateId);
    }

    @GetMapping("/check")
    public boolean hasApplied(
            @RequestParam Long candidateId,
            @RequestParam Long jobId) {

        return applicationService.hasApplied(candidateId, jobId);
    }
    @GetMapping("/job/{jobId}/applicants")
    public List<ApplicantDTO> getApplicantsByJob(
            @PathVariable Long jobId) {

        return applicationService.getApplicantsByJob(jobId);
    }

    // ==========================
    // Recruiter Applications API
    // ==========================

    @GetMapping("/recruiter/{recruiterId}/jobs-with-application-count")
    public List<RecruiterJobDTO> getRecruiterJobs(
            @PathVariable Long recruiterId) {

        return applicationService.getRecruiterJobsWithApplicationCount(recruiterId);
    }
    @PutMapping("/{applicationId}/status")
    public Application updateApplicationStatus(
            @PathVariable Long applicationId,
            @RequestParam String status) {

        return applicationService.updateApplicationStatus(applicationId, status);
    }
}