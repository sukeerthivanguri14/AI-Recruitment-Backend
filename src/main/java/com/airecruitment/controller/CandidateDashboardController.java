package com.airecruitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.airecruitment.dto.CandidateDashboardDTO;
import com.airecruitment.service.CandidateDashboardService;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class CandidateDashboardController {

    @Autowired
    private CandidateDashboardService dashboardService;

    @GetMapping("/candidate/{candidateId}")
    public CandidateDashboardDTO getDashboard(
            @PathVariable Long candidateId) {

        return dashboardService.getDashboard(candidateId);

    }

}