package com.airecruitment.service;

import java.util.Optional;

import com.airecruitment.entity.Candidate;

public interface CandidateService {

    Candidate saveCandidate(Candidate candidate);

    Optional<Candidate> getCandidateById(Long id);

    Optional<Candidate> getCandidateByEmail(String email);

    Candidate updateCandidate(Long id, Candidate candidate);
}