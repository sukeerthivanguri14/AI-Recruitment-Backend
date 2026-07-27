package com.airecruitment.repository;

import com.airecruitment.entity.Application;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findByCandidateId(Long candidateId);

    List<Application> findByJobId(Long jobId);

    boolean existsByCandidateIdAndJobId(Long candidateId, Long jobId);

    long countByJobId(Long jobId);  
    // <-- ADD THIS
    long count();

    long countByApplicationStatus(String applicationStatus);
    long countByCandidateId(Long candidateId);

    long countByCandidateIdAndApplicationStatus(
            Long candidateId,
            String applicationStatus
    );
    @Query("""
    		SELECT COUNT(a)
    		FROM Application a
    		JOIN Job j ON a.jobId = j.jobId
    		WHERE j.recruiterId = :recruiterId
    		""")
    		long countApplicationsByRecruiter(@Param("recruiterId") Long recruiterId);

    		@Query("""
    		SELECT COUNT(a)
    		FROM Application a
    		JOIN Job j ON a.jobId = j.jobId
    		WHERE j.recruiterId = :recruiterId
    		AND a.applicationStatus = :status
    		""")
    		long countApplicationsByRecruiterAndStatus(
    		        @Param("recruiterId") Long recruiterId,
    		        @Param("status") String status);
    Application findByCandidateIdAndJobId(Long candidateId, Long jobId);
}