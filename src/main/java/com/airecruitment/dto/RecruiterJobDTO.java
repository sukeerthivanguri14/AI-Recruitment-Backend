package com.airecruitment.dto;

public class RecruiterJobDTO {

    private Long jobId;
    private String jobTitle;
    private String companyName;
    private Long applicationCount;
    private String skillsRequired;

    public RecruiterJobDTO() {
    }

    public RecruiterJobDTO(Long jobId,
            String jobTitle,
            String companyName,
            String skillsRequired,
            Long applicationCount) {

this.jobId = jobId;
this.jobTitle = jobTitle;
this.companyName = companyName;
this.skillsRequired = skillsRequired;
this.applicationCount = applicationCount;
}

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getApplicationCount() {
        return applicationCount;
    }
    public String getSkillsRequired() {
        return skillsRequired;
    }

    public void setSkillsRequired(String skillsRequired) {
        this.skillsRequired = skillsRequired;
    }

    public void setApplicationCount(Long applicationCount) {
        this.applicationCount = applicationCount;
    }
}