package com.airecruitment.service;

import com.airecruitment.dto.DashboardDTO;
import com.airecruitment.repository.ApplicationRepository;
import com.airecruitment.repository.CandidateRepository;
import com.airecruitment.repository.InterviewRepository;
import com.airecruitment.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private InterviewRepository interviewRepository;

    public DashboardDTO getDashboardSummary(Long recruiterId) {

        DashboardDTO dto = new DashboardDTO();

        // Dashboard Cards
        
        dto.setActiveJobs(
                jobRepository.countByRecruiterId(recruiterId));

        dto.setApplications(
                applicationRepository.countApplicationsByRecruiter(recruiterId));

        dto.setApplied(
                applicationRepository.countApplicationsByRecruiterAndStatus(
                        recruiterId, "Applied"));

        dto.setInterviewScheduled(
                applicationRepository.countApplicationsByRecruiterAndStatus(
                        recruiterId, "Interview Scheduled"));

        dto.setSelected(
                applicationRepository.countApplicationsByRecruiterAndStatus(
                        recruiterId, "Selected"));

        dto.setRejected(
                applicationRepository.countApplicationsByRecruiterAndStatus(
                        recruiterId, "Rejected"));

        dto.setResumesScreened(
                applicationRepository.countApplicationsByRecruiter(recruiterId));
        dto.setCandidates(candidateRepository.count());

        dto.setTodayInterviews(
            interviewRepository.countByInterviewDate(LocalDate.now())
        );

        dto.setPendingScreening(
                applicationRepository.countApplicationsByRecruiterAndStatus(
                        recruiterId, "Applied"));
        // Recent Activity (temporary)
        List<String> activities = new ArrayList<>();

        activities.add("Dashboard loaded successfully.");

        dto.setRecentActivities(activities);

        return dto;
    }

}