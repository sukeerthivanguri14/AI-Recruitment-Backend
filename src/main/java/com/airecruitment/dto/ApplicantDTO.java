package com.airecruitment.dto;

public class ApplicantDTO {

    private Long applicationId;
    private Long candidateId;
    private String candidateName;
    private String email;
    private String phone;
    private String education;
    private String skills;
    private String resumePath;
    private String applicationStatus;
    private boolean interviewScheduled;

    public ApplicantDTO() {
    }

    public ApplicantDTO(
            Long applicationId,
            Long candidateId,
            String candidateName,
            String email,
            String phone,
            String education,
            String skills,
            String resumePath,
            String applicationStatus) {

        this.applicationId = applicationId;
        this.candidateId = candidateId;
        this.candidateName = candidateName;
        this.email = email;
        this.phone = phone;
        this.education = education;
        this.skills = skills;
        this.resumePath = resumePath;
        this.applicationStatus = applicationStatus;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getResumePath() {
        return resumePath;
    }

    public void setResumePath(String resumePath) {
        this.resumePath = resumePath;
    }
    public String getApplicationStatus() {
        return applicationStatus;
    }
    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }
    public boolean isInterviewScheduled() {
        return interviewScheduled;
    }

    public void setInterviewScheduled(boolean interviewScheduled) {
        this.interviewScheduled = interviewScheduled;
    }
}