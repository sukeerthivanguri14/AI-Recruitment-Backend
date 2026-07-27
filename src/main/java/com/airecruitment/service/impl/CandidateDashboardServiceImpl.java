package com.airecruitment.service.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airecruitment.dto.CandidateDashboardDTO;
import com.airecruitment.repository.ApplicationRepository;
import com.airecruitment.repository.InterviewRepository;
import com.airecruitment.repository.JobRepository;
import com.airecruitment.service.CandidateDashboardService;

@Service
public class CandidateDashboardServiceImpl
        implements CandidateDashboardService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private InterviewRepository interviewRepository;

    @Autowired
    private JobRepository jobRepository;

    @Override
    public CandidateDashboardDTO getDashboard(Long candidateId) {

        CandidateDashboardDTO dto = new CandidateDashboardDTO();

        dto.setAppliedJobs(
                applicationRepository.countByCandidateId(candidateId)
        );

        dto.setInterviews(
                interviewRepository.countByCandidateId(candidateId)
        );

        dto.setOffers(
                applicationRepository
                        .countByCandidateIdAndApplicationStatus(
                                candidateId,
                                "Selected"
                        )
        );

        dto.setUpcomingInterviews(

                interviewRepository
                        .findByCandidateIdAndInterviewDateGreaterThanEqualOrderByInterviewDateAsc(
                                candidateId,
                                LocalDate.now()
                        )
        );

        dto.setLatestJobs(

                jobRepository.findTop5ByStatusOrderByJobIdDesc("Open")

        );

        return dto;

    }

}