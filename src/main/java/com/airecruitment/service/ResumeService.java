package com.airecruitment.service;

import java.util.List;

import com.airecruitment.entity.Resume;


public interface ResumeService {


    Resume saveResume(Resume resume);


    List<Resume> getAllResumes();

}