package com.airecruitment.dto;

import java.util.List;
import com.airecruitment.entity.Interview;
import com.airecruitment.entity.Job;

public class CandidateDashboardDTO {

    private long appliedJobs;
    private long interviews;
    private long offers;

    private List<Interview> upcomingInterviews;
    private List<Job> latestJobs;

    public long getAppliedJobs() {
        return appliedJobs;
    }

    public void setAppliedJobs(long appliedJobs) {
        this.appliedJobs = appliedJobs;
    }

    public long getInterviews() {
        return interviews;
    }

    public void setInterviews(long interviews) {
        this.interviews = interviews;
    }

    public long getOffers() {
        return offers;
    }

    public void setOffers(long offers) {
        this.offers = offers;
    }

    public List<Interview> getUpcomingInterviews() {
        return upcomingInterviews;
    }

    public void setUpcomingInterviews(List<Interview> upcomingInterviews) {
        this.upcomingInterviews = upcomingInterviews;
    }

    public List<Job> getLatestJobs() {
        return latestJobs;
    }

    public void setLatestJobs(List<Job> latestJobs) {
        this.latestJobs = latestJobs;
    }
}