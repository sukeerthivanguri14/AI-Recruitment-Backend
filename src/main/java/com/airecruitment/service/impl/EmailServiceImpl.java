package com.airecruitment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.airecruitment.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendInterviewEmail(
            String to,
            String candidateName,
            String interviewDate,
            String interviewTime,
            String mode,
            String meetingLink,
            String interviewerName) {

        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setTo(to);
            message.setSubject("Interview Scheduled");

            message.setText(
                    "Dear " + candidateName + ",\n\n"
                    + "Congratulations! Your interview has been scheduled.\n\n"
                    + "Interview Details:\n"
                    + "Date : " + interviewDate + "\n"
                    + "Time : " + interviewTime + "\n"
                    + "Mode : " + mode + "\n"
                    + "Interviewer : " + interviewerName + "\n"
                    + "Meeting Link : " + meetingLink + "\n\n"
                    + "Best Wishes,\n"
                    + "AI Recruitment Team"
            );

            System.out.println("Sending email to: " + to);

            mailSender.send(message);

            System.out.println("Email sent successfully!");

        } catch (Exception e) {
            System.out.println("Email sending failed!");
            e.printStackTrace();
        }
    }

    @Override
    public void sendSelectionEmail(String to, String candidateName) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setSubject("Congratulations!");

        message.setText(
                "Dear " + candidateName + ",\n\n"
                + "Congratulations!\n\n"
                + "You have successfully cleared the interview and have been selected.\n\n"
                + "Our HR team will contact you shortly.\n\n"
                + "Regards,\n"
                + "AI Recruitment Team"
        );

        mailSender.send(message);
    }

    @Override
    public void sendRejectionEmail(String to, String candidateName) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setSubject("Application Status");

        message.setText(
                "Dear " + candidateName + ",\n\n"
                + "Thank you for attending the interview.\n\n"
                + "Unfortunately, you were not selected for this position.\n\n"
                + "We wish you all the best for your future.\n\n"
                + "Regards,\n"
                + "AI Recruitment Team"
        );

        mailSender.send(message);
    }
}