package com.airecruitment.service.impl;

import com.airecruitment.entity.Job;
import com.airecruitment.repository.JobRepository;
import com.airecruitment.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Override
    public Job saveJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }
    @Override
    public List<Job> getJobsByCompany(String companyName) {

        return jobRepository.findByCompanyName(companyName);

    }
    @Override
    public List<Job> getOpenJobs() {

        return jobRepository.findByStatus("Open");

    }

    @Override
    public Optional<Job> getJobById(Long id) {
        return jobRepository.findById(id);
    }

    @Override
    public Job updateJob(Long id, Job job) {

        Job existingJob = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        existingJob.setJobTitle(job.getJobTitle());
        existingJob.setCompanyName(job.getCompanyName());
        existingJob.setLocation(job.getLocation());
        existingJob.setEmploymentType(job.getEmploymentType());
        existingJob.setExperienceRequired(job.getExperienceRequired());
        existingJob.setSalary(job.getSalary());
        existingJob.setPositionsAvailable(job.getPositionsAvailable()); // NEW
        existingJob.setSkillsRequired(job.getSkillsRequired());
        existingJob.setJobDescription(job.getJobDescription());
        existingJob.setApplicationDeadline(job.getApplicationDeadline());
        existingJob.setStatus(job.getStatus());
        existingJob.setRecruiterId(job.getRecruiterId());

        return jobRepository.save(existingJob);
    }
    @Override
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
}