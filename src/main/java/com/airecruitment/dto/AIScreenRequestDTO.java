package com.airecruitment.dto;

public class AIScreenRequestDTO {

    private Long jobId;
    private Integer shortlistCount;

    public AIScreenRequestDTO() {
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Integer getShortlistCount() {
        return shortlistCount;
    }

    public void setShortlistCount(Integer shortlistCount) {
        this.shortlistCount = shortlistCount;
    }
}