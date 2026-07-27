package com.airecruitment.service;

import com.airecruitment.dto.AIShortlistedCandidateDTO;
import com.airecruitment.entity.Application;
import com.airecruitment.entity.Candidate;
import com.airecruitment.entity.Job;
import com.airecruitment.entity.Resume;
import com.airecruitment.repository.ApplicationRepository;
import com.airecruitment.repository.CandidateRepository;
import com.airecruitment.repository.JobRepository;
import com.airecruitment.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class AIShortlistingService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private ResumeScreeningService resumeScreeningService;
    public List<AIShortlistedCandidateDTO> shortlist(Long jobId, Integer shortlistCount) throws Exception{

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job Not Found"));

        String requiredSkills = job.getSkillsRequired();

        

        Integer positions = shortlistCount;

        if (positions == null || positions <= 0) {
            throw new RuntimeException("Please enter a valid shortlist count.");
        }
        // ================= DEBUG =================
        System.out.println("Job ID Received = " + jobId);

        List<Application> allApplications = applicationRepository.findAll();

        System.out.println("Total Applications in Database = " + allApplications.size());

        for (Application a : allApplications) {
            System.out.println(
                    "Application ID = " + a.getId()
                            + " | Candidate ID = " + a.getCandidateId()
                            + " | Job ID = " + a.getJobId()
            );
        }
        // =========================================

        List<Application> applications = applicationRepository.findByJobId(jobId);

        System.out.println("==================================");
        System.out.println("Applications Found For Job " + jobId + " = " + applications.size());
        System.out.println("==================================");

        List<AIShortlistedCandidateDTO> shortlisted = new ArrayList<>();

        for (Application application : applications) {

            System.out.println("----------------------------------");
            System.out.println("Candidate ID = " + application.getCandidateId());

            Candidate candidate = candidateRepository
                    .findByCandidateId(application.getCandidateId())
                    .orElse(null);

            if (candidate == null) {
                System.out.println("Candidate NOT FOUND");
                continue;
            }

            System.out.println("Candidate Found : " + candidate.getFullName());
            System.out.println("Email : " + candidate.getEmail());

            if (candidate.getResumePath() == null || candidate.getResumePath().isBlank()) {
                System.out.println("Resume NOT FOUND");
                continue;
            }
            System.out.println("Resume Path = " + candidate.getResumePath());
            System.out.println("File Exists = " + new java.io.File(candidate.getResumePath()).exists());

            double score;

            try {
                score = resumeScreeningService.screenResume(
                        candidate.getResumePath(),
                        requiredSkills
                );
            } catch (Exception e) {
                System.out.println("Failed to screen resume: " + candidate.getResumePath());
                e.printStackTrace();
                continue; // Skip this candidate and process the rest
            }

            shortlisted.add(
                new AIShortlistedCandidateDTO(
                        candidate.getCandidateId(),
                        candidate.getFullName(),
                        candidate.getEmail(),
                        candidate.getResumePath(),
                        score,
                        false
                )
            );

          
        }

        shortlisted.sort(
                Comparator.comparingDouble(AIShortlistedCandidateDTO::getScore)
                        .reversed()
        );

        System.out.println("Total Shortlisted = " + shortlisted.size());

        for (int i = 0; i < shortlisted.size(); i++) {

            shortlisted.get(i).setShortlisted(i < positions);

        }

        return shortlisted;
    }
   
}