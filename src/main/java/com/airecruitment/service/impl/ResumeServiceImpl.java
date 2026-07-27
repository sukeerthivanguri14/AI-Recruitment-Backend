package com.airecruitment.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airecruitment.entity.Resume;
import com.airecruitment.repository.ResumeRepository;
import com.airecruitment.service.ResumeService;


@Service
public class ResumeServiceImpl implements ResumeService{


    @Autowired
    private ResumeRepository resumeRepository;



    @Override
    public Resume saveResume(Resume resume){

        return resumeRepository.save(resume);

    }



    @Override
    public List<Resume> getAllResumes(){

        return resumeRepository.findAll();

    }


}