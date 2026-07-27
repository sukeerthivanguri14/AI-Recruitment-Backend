package com.airecruitment.ai;

import java.util.List;

public class MatchCalculator {

    public static double calculateMatch(List<String> resumeSkills,
                                        List<String> jobSkills) {

        if (jobSkills == null || jobSkills.isEmpty()) {
            return 0;
        }

        int matched = 0;

        for (String skill : jobSkills) {

            if (resumeSkills.contains(skill)) {
                matched++;
            }

        }

        return ((double) matched / jobSkills.size()) * 100;
    }

}