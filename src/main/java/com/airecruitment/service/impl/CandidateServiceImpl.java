package com.airecruitment.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airecruitment.entity.Candidate;
import com.airecruitment.repository.CandidateRepository;
import com.airecruitment.service.CandidateService;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public Candidate saveCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    @Override
    public Optional<Candidate> getCandidateById(Long id) {
        return candidateRepository.findById(id);
    }

    @Override
    public Optional<Candidate> getCandidateByEmail(String email) {
        return candidateRepository.findByEmail(email);
    }

    @Override
    public Candidate updateCandidate(Long id, Candidate updatedCandidate) {

        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        candidate.setFullName(updatedCandidate.getFullName());
        candidate.setEmail(updatedCandidate.getEmail());
        candidate.setPhone(updatedCandidate.getPhone());
        candidate.setLocation(updatedCandidate.getLocation());
        candidate.setEducation(updatedCandidate.getEducation());
        candidate.setSkills(updatedCandidate.getSkills());
        candidate.setExperience(updatedCandidate.getExperience());
        candidate.setLinkedin(updatedCandidate.getLinkedin());
        candidate.setGithub(updatedCandidate.getGithub());
        candidate.setResumePath(updatedCandidate.getResumePath());
        candidate.setAbout(updatedCandidate.getAbout());

        return candidateRepository.save(candidate);
    }
}