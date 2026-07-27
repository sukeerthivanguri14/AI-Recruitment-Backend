package com.airecruitment.entity;

import jakarta.persistence.*;

@Entity
@Table(name="resumes")
public class Resume {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String candidateName;

    private String email;

    private String fileName;
    private String filePath;


    public Resume() {
    }


    public Resume(String candidateName, String email, String fileName) {
        this.candidateName = candidateName;
        this.email = email;
        this.fileName = fileName;
    }


    public Long getId() {
        return id;
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


    public String getFileName() {
        return fileName;
    }


    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

}