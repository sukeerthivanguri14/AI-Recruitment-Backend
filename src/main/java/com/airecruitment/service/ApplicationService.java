package com.airecruitment.service;

import com.airecruitment.dto.MyApplicationDTO;
import com.airecruitment.entity.Application;
import com.airecruitment.dto.RecruiterJobDTO;
import com.airecruitment.dto.ApplicantDTO;


import java.util.List;

public interface ApplicationService {

    Application applyJob(Application application);

    List<Application> getAllApplications();

    List<ApplicantDTO> getApplicantsByJob(Long jobId);

    List<Application> getApplicationsByCandidate(Long candidateId);

    List<Application> getApplicationsByJob(Long jobId);

    boolean hasApplied(Long candidateId, Long jobId);

    List<MyApplicationDTO> getMyApplications(Long candidateId);

    List<RecruiterJobDTO> getRecruiterJobsWithApplicationCount(Long recruiterId);

    Application updateApplicationStatus(Long applicationId, String status);
}