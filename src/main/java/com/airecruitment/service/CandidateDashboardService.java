package com.airecruitment.service;

import com.airecruitment.dto.CandidateDashboardDTO;

public interface CandidateDashboardService {

    CandidateDashboardDTO getDashboard(Long candidateId);

}