package com.airecruitment.dto;

import java.util.List;

public class DashboardDTO {

    private long activeJobs;
    private long candidates;
    private long applications;
    private long todayInterviews;

    private long applied;
    private long interviewScheduled;
    private long selected;
    private long rejected;

    private long resumesScreened;
    private double averageMatchScore;
    private long pendingScreening;

    private List<String> recentActivities;

    public DashboardDTO() {
    }

    public long getActiveJobs() {
        return activeJobs;
    }

    public void setActiveJobs(long activeJobs) {
        this.activeJobs = activeJobs;
    }

    public long getCandidates() {
        return candidates;
    }

    public void setCandidates(long candidates) {
        this.candidates = candidates;
    }

    public long getApplications() {
        return applications;
    }

    public void setApplications(long applications) {
        this.applications = applications;
    }

    public long getTodayInterviews() {
        return todayInterviews;
    }

    public void setTodayInterviews(long todayInterviews) {
        this.todayInterviews = todayInterviews;
    }

    public long getApplied() {
        return applied;
    }

    public void setApplied(long applied) {
        this.applied = applied;
    }

    public long getInterviewScheduled() {
        return interviewScheduled;
    }

    public void setInterviewScheduled(long interviewScheduled) {
        this.interviewScheduled = interviewScheduled;
    }

    public long getSelected() {
        return selected;
    }

    public void setSelected(long selected) {
        this.selected = selected;
    }

    public long getRejected() {
        return rejected;
    }

    public void setRejected(long rejected) {
        this.rejected = rejected;
    }

    public long getResumesScreened() {
        return resumesScreened;
    }

    public void setResumesScreened(long resumesScreened) {
        this.resumesScreened = resumesScreened;
    }

    public double getAverageMatchScore() {
        return averageMatchScore;
    }

    public void setAverageMatchScore(double averageMatchScore) {
        this.averageMatchScore = averageMatchScore;
    }

    public long getPendingScreening() {
        return pendingScreening;
    }

    public void setPendingScreening(long pendingScreening) {
        this.pendingScreening = pendingScreening;
    }

    public List<String> getRecentActivities() {
        return recentActivities;
    }

    public void setRecentActivities(List<String> recentActivities) {
        this.recentActivities = recentActivities;
    }
}