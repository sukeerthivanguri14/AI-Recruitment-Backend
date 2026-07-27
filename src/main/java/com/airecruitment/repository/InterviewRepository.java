package com.airecruitment.repository;

import java.util.List;
import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airecruitment.entity.Interview;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long> {

    List<Interview> findByCandidateId(Long candidateId);

    List<Interview> findByJobId(Long jobId);
    long countByInterviewDate(LocalDate interviewDate);

    List<Interview> findByInterviewDate(LocalDate interviewDate);
    long countByCandidateId(Long candidateId);
    boolean existsByCandidateIdAndJobId(Long candidateId, Long jobId);

    List<Interview> findByCandidateIdAndInterviewDateGreaterThanEqualOrderByInterviewDateAsc(
            Long candidateId,
            LocalDate interviewDate
    );

}