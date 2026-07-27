package com.airecruitment.dto;

public class AIShortlistedCandidateDTO {

    private Long candidateId;
    private String candidateName;
    private String email;
    private String resumePath;
    private double score;
    private boolean shortlisted;

    public AIShortlistedCandidateDTO() {
    }

    public AIShortlistedCandidateDTO(
            Long candidateId,
            String candidateName,
            String email,
            String resumePath,
            double score,
            boolean shortlisted) {

        this.candidateId = candidateId;
        this.candidateName = candidateName;
        this.email = email;
        this.resumePath = resumePath;
        this.score = score;
        this.shortlisted = shortlisted;
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

    public String getResumePath() {
        return resumePath;
    }

    public void setResumePath(String resumePath) {
        this.resumePath = resumePath;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
    public boolean isShortlisted() {
        return shortlisted;
    }

    public void setShortlisted(boolean shortlisted) {
        this.shortlisted = shortlisted;
    }
}