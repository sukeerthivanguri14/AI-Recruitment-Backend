package com.airecruitment.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import com.airecruitment.ai.SkillExtractor;

@Service
public class ResumeScreeningService {

    public double screenResume(String resumePath, String requiredSkills) throws IOException {

        File file = new File(resumePath);

        PDDocument document = Loader.loadPDF(file);

        PDFTextStripper stripper = new PDFTextStripper();

        String resumeText = stripper.getText(document);

        System.out.println("===== RESUME TEXT =====");
        System.out.println(resumeText);
        System.out.println("=======================");

        document.close();


        // Extract skills from resume
        List<String> extractedSkills =
                SkillExtractor.extractSkills(resumeText);


        System.out.println("Extracted Skills: " + extractedSkills);


        // Convert extracted skills into lowercase
        List<String> normalizedExtractedSkills =
                extractedSkills.stream()
                .map(skill -> skill.trim().toLowerCase())
                .toList();


        // Required skills
        String[] required =
                requiredSkills.split(",");


        int matched = 0;


        // Compare skills
        for(String skill : required){

            String requiredSkill = skill.trim().toLowerCase();


            if(normalizedExtractedSkills.contains(requiredSkill)){

                matched++;

            }

        }


        System.out.println("Required Skills: " + requiredSkills);
        System.out.println("Matched Skills: " + matched);
        System.out.println("Total Skills: " + required.length);


        double percentage =
                (matched * 100.0) / required.length;


        System.out.println("Match Percentage: " + percentage + "%");


        return percentage;

    }

}