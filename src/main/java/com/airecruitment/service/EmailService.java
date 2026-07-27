package com.airecruitment.service;

public interface EmailService {

    void sendInterviewEmail(
            String to,
            String candidateName,
            String interviewDate,
            String interviewTime,
            String mode,
            String meetingLink,
            String interviewerName);

    void sendSelectionEmail(
            String to,
            String candidateName);

    void sendRejectionEmail(
            String to,
            String candidateName);
}