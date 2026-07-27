package com.airecruitment.controller;

import com.airecruitment.dto.AIScreenRequestDTO;
import com.airecruitment.dto.AIShortlistedCandidateDTO;
import com.airecruitment.service.AIShortlistingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin(origins = "*")
public class AIController {

    @Autowired
    private AIShortlistingService aiShortlistingService;

    @PostMapping("/screen")
    public ResponseEntity<List<AIShortlistedCandidateDTO>> screenResumes(
            @RequestBody AIScreenRequestDTO request) throws Exception {

        return ResponseEntity.ok(
                aiShortlistingService.shortlist(
                        request.getJobId(),
                        request.getShortlistCount()
                )
        );
    }
}