package com.airecruitment.repository;

import com.airecruitment.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findByCompanyName(String companyName);

    List<Job> findByStatus(String status);
    List<Job> findByRecruiterId(Long recruiterId);
    long count();

    long countByStatus(String status);
    List<Job> findTop5ByStatusOrderByJobIdDesc(String status);
    long countByRecruiterId(Long recruiterId);
   

}