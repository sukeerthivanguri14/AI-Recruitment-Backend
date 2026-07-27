package com.airecruitment.service;

import com.airecruitment.entity.Job;

import java.util.List;
import java.util.Optional;

public interface JobService {

    Job saveJob(Job job);

    List<Job> getAllJobs();

    List<Job> getJobsByCompany(String companyName);

    List<Job> getOpenJobs();

    Optional<Job> getJobById(Long id);

    Job updateJob(Long id, Job job);

    void deleteJob(Long id);

}