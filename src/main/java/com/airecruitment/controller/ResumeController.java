package com.airecruitment.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.airecruitment.entity.Candidate;
import com.airecruitment.entity.Resume;
import com.airecruitment.repository.CandidateRepository;
import com.airecruitment.service.ResumeService;

@RestController
@RequestMapping("/api/resumes")
@CrossOrigin(origins = "*")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private CandidateRepository candidateRepository;

    @PostMapping("/upload")
    public Resume uploadResume(
            @RequestParam String candidateName,
            @RequestParam String email,
            @RequestParam MultipartFile file
    ) throws IOException {

        System.out.println("========== UPLOAD START ==========");
        System.out.println("Candidate : " + candidateName);
        System.out.println("Email     : " + email);
        System.out.println("File      : " + file.getOriginalFilename());

        String folder = "uploads/resumes/";

        Files.createDirectories(Paths.get(folder));

        String filePath = folder + file.getOriginalFilename();

        Files.write(Paths.get(filePath), file.getBytes());

        System.out.println("Saved To : " + Paths.get(filePath).toAbsolutePath());
        System.out.println("Exists ? : " + Files.exists(Paths.get(filePath)));

        Resume resume = new Resume();

        resume.setCandidateName(candidateName);
        resume.setEmail(email);
        resume.setFileName(file.getOriginalFilename());
        resume.setFilePath(filePath);

        Resume savedResume = resumeService.saveResume(resume);
        System.out.println("Email Received = [" + email + "]");
        System.out.println("Email Length   = " + email.length());
        System.out.println("All Candidates:");

        candidateRepository.findAll().forEach(c ->
            System.out.println(c.getEmail())
        );

        Candidate candidate = candidateRepository
        		.findByEmail(email.trim())
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        candidate.setResumePath(filePath);
        

        candidateRepository.save(candidate);

        System.out.println("DB Path Saved : " + candidate.getResumePath());
        System.out.println("========== UPLOAD END ==========");

        return savedResume;
    }

    @GetMapping("/view/{candidateId}")
    public ResponseEntity<Resource> viewResume(@PathVariable Long candidateId)
            throws IOException {
    	System.out.println("VIEW RESUME API CALLED");

        Candidate candidate = candidateRepository
                .findByCandidateId(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        String filePath = candidate.getResumePath();

     // If only filename is stored in DB, prepend the uploads folder
     if (!filePath.startsWith("uploads")) {
         filePath = "uploads/resumes/" + filePath;
     }

     Path path = Paths.get(filePath);

     Resource resource = new UrlResource(path.toUri());

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }
}