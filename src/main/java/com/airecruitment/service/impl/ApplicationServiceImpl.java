package com.airecruitment.service.impl;

import java.time.LocalDate;
import com.airecruitment.repository.InterviewRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.airecruitment.dto.ApplicantDTO;
import com.airecruitment.dto.MyApplicationDTO;
import com.airecruitment.dto.RecruiterJobDTO;
import com.airecruitment.entity.Application;
import com.airecruitment.entity.Candidate;
import com.airecruitment.entity.Job;
import com.airecruitment.entity.Notification;
import com.airecruitment.repository.ApplicationRepository;
import com.airecruitment.repository.CandidateRepository;
import com.airecruitment.repository.JobRepository;
import com.airecruitment.repository.NotificationRepository;
import com.airecruitment.service.ApplicationService;
import com.airecruitment.service.EmailService;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final NotificationRepository notificationRepository;
    private final CandidateRepository candidateRepository;
    private final EmailService emailService;
    private final InterviewRepository interviewRepository;

    public ApplicationServiceImpl(
            ApplicationRepository applicationRepository,
            JobRepository jobRepository,
            NotificationRepository notificationRepository,
            CandidateRepository candidateRepository,
            EmailService emailService,
            InterviewRepository interviewRepository) {

        this.applicationRepository = applicationRepository;
        this.jobRepository = jobRepository;
        this.notificationRepository = notificationRepository;
        this.candidateRepository = candidateRepository;
        this.emailService = emailService;
        this.interviewRepository = interviewRepository;
    }

    @Override
    public Application applyJob(Application application) {

        if (applicationRepository.existsByCandidateIdAndJobId(
                application.getCandidateId(),
                application.getJobId())) {

            throw new RuntimeException("Already Applied");
        }

        application.setStatus("Applied");
        application.setApplicationStatus("Applied");
        application.setAppliedDate(LocalDate.now());

        Application savedApplication = applicationRepository.save(application);

        Job job = jobRepository.findById(application.getJobId()).orElse(null);

        if (job != null) {

            Notification notification = new Notification();

            notification.setCandidateId(application.getCandidateId());
            notification.setTitle("Application Submitted");
            notification.setMessage(
                    "You have successfully applied for "
                            + job.getJobTitle()
                            + " at "
                            + job.getCompanyName() + ".");

            notification.setType("INFO");
            notification.setRead(false);
            notification.setCreatedAt(LocalDateTime.now());

            notificationRepository.save(notification);
        }

        return savedApplication;
    }

    @Override
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    @Override
    public List<Application> getApplicationsByCandidate(Long candidateId) {
        return applicationRepository.findByCandidateId(candidateId);
    }

    @Override
    public List<Application> getApplicationsByJob(Long jobId) {
        return applicationRepository.findByJobId(jobId);
    }

    @Override
    public boolean hasApplied(Long candidateId, Long jobId) {
        return applicationRepository.existsByCandidateIdAndJobId(candidateId, jobId);
    }

    @Override
    public List<ApplicantDTO> getApplicantsByJob(Long jobId) {

        List<Application> applications = applicationRepository.findByJobId(jobId);

        List<ApplicantDTO> applicants = new ArrayList<>();

        for (Application application : applications) {

            Candidate candidate = candidateRepository
                    .findByCandidateId(application.getCandidateId())
                    .orElse(null);

            if (candidate != null) {

            	ApplicantDTO dto = new ApplicantDTO(
            	        application.getId(),
            	        candidate.getCandidateId(),
            	        candidate.getFullName(),
            	        candidate.getEmail(),
            	        candidate.getPhone(),
            	        candidate.getEducation(),
            	        candidate.getSkills(),
            	        candidate.getResumePath(),
            	        application.getApplicationStatus()
            	);

            	dto.setInterviewScheduled(
            	        interviewRepository.existsByCandidateIdAndJobId(
            	                candidate.getCandidateId(),
            	                application.getJobId()
            	        )
            	);

            	applicants.add(dto);
            	
            }
        }

        return applicants;
    }

    @Override
    public List<MyApplicationDTO> getMyApplications(Long candidateId) {

        List<Application> applications =
                applicationRepository.findByCandidateId(candidateId);

        List<MyApplicationDTO> response = new ArrayList<>();

        for (Application application : applications) {

            Job job = jobRepository.findById(application.getJobId()).orElse(null);

            if (job != null) {

                response.add(new MyApplicationDTO(
                        application.getId(),
                        job.getJobId(),
                        job.getJobTitle(),
                        job.getCompanyName(),
                        job.getLocation(),
                        job.getEmploymentType(),
                        application.getAppliedDate()));
            }
        }

        return response;
    }

    @Override
    public List<RecruiterJobDTO> getRecruiterJobsWithApplicationCount(Long recruiterId) {

        List<Job> jobs = jobRepository.findByRecruiterId(recruiterId);

        List<RecruiterJobDTO> response = new ArrayList<>();

        for (Job job : jobs) {

            long count = applicationRepository.countByJobId(job.getJobId());

            response.add(new RecruiterJobDTO(
                    job.getJobId(),
                    job.getJobTitle(),
                    job.getCompanyName(),
                    job.getSkillsRequired(),
                    count));
        }

        return response;
    }

    @Override
    public Application updateApplicationStatus(Long applicationId, String status) {

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        application.setApplicationStatus(status);

        Application savedApplication = applicationRepository.save(application);

        Candidate candidate = candidateRepository
                .findByCandidateId(application.getCandidateId())
                .orElse(null);

        if (candidate != null) {

            if ("Selected".equalsIgnoreCase(status)) {

                emailService.sendSelectionEmail(
                        candidate.getEmail(),
                        candidate.getFullName());

            } else if ("Rejected".equalsIgnoreCase(status)) {

                emailService.sendRejectionEmail(
                        candidate.getEmail(),
                        candidate.getFullName());
            }
        }

        return savedApplication;
    }
}