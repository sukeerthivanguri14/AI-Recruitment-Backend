package com.airecruitment.ai;

import java.util.ArrayList;
import java.util.List;

public class SkillExtractor {

    private static final String[] SKILLS = {
            "Java",
            "Spring",
            "Spring Boot",
            "React",
            "Angular",
            "JavaScript",
            "Python",
            "MySQL",
            "SQL",
            "MongoDB",
            "HTML",
            "CSS",
            "Docker",
            "Kubernetes",
            "Git",
            "GitHub",
            "AWS",
            "Azure",
            "REST API",
            "Hibernate"
    };

    public static List<String> extractSkills(String resumeText) {

        List<String> foundSkills = new ArrayList<>();

        String lowerText = resumeText.toLowerCase();

        for (String skill : SKILLS) {

            if (lowerText.contains(skill.toLowerCase())) {

                foundSkills.add(skill);

            }

        }

        return foundSkills;
    }
}
