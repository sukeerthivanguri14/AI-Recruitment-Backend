package com.airecruitment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airecruitment.dto.InterviewRequestDTO;
import com.airecruitment.entity.Application;
import com.airecruitment.entity.Candidate;
import com.airecruitment.entity.Interview;
import com.airecruitment.repository.ApplicationRepository;
import com.airecruitment.repository.CandidateRepository;
import com.airecruitment.repository.InterviewRepository;
import com.airecruitment.service.EmailService;
import com.airecruitment.service.InterviewService;

@Service
public class InterviewServiceImpl implements InterviewService {

    @Autowired
    private InterviewRepository interviewRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public Interview scheduleInterview(InterviewRequestDTO request) {

        // 1. Prevent duplicate interview
        if (interviewRepository.existsByCandidateIdAndJobId(
                request.getCandidateId(),
                request.getJobId())) {

            throw new RuntimeException("Interview already scheduled for this candidate.");
        }

        // 2. Candidate must have applied
        if (!applicationRepository.existsByCandidateIdAndJobId(
                request.getCandidateId(),
                request.getJobId())) {

            throw new RuntimeException("Candidate has not applied for this job.");
        }

        // 3. Get application
        Application application = applicationRepository.findByCandidateIdAndJobId(
                request.getCandidateId(),
                request.getJobId());

        // 4. Application must be in Applied status
        if (!"Applied".equals(application.getApplicationStatus())) {
            throw new RuntimeException(
                    "Interview cannot be scheduled. Application is not in Applied status.");
        }

        // 5. Create interview
        Interview interview = new Interview();

        interview.setCandidateId(request.getCandidateId());
        interview.setJobId(request.getJobId());
        interview.setInterviewDate(request.getInterviewDate());
        interview.setInterviewTime(request.getInterviewTime());
        interview.setMode(request.getMode());
        interview.setMeetingLink(request.getMeetingLink());
        interview.setInterviewerName(request.getInterviewerName());
        interview.setNotes(request.getNotes());
        interview.setStatus("Scheduled");

        // 6. Save interview
        Interview savedInterview = interviewRepository.save(interview);

        // 7. Update application status
        application.setApplicationStatus("Interview Scheduled");
        applicationRepository.save(application);

        // 8. Send email
        Candidate candidate = candidateRepository
                .findByCandidateId(request.getCandidateId())
                .orElse(null);

        if (candidate != null) {

            emailService.sendInterviewEmail(
                    candidate.getEmail(),
                    candidate.getFullName(),
                    request.getInterviewDate().toString(),
                    request.getInterviewTime().toString(),
                    request.getMode(),
                    request.getMeetingLink(),
                    request.getInterviewerName());
        }

        return savedInterview;
    }
    @Override
    public List<Interview> getAllInterviews() {
        return interviewRepository.findAll();
    }
}