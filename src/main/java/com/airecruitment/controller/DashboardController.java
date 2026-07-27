package com.airecruitment.controller;

import com.airecruitment.dto.DashboardDTO;
import com.airecruitment.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;
    @GetMapping("/summary/{recruiterId}")
    public DashboardDTO getDashboardSummary(
            @PathVariable Long recruiterId) {

        return dashboardService.getDashboardSummary(recruiterId);
    }
}