package com.example.demo.controller;

import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@CrossOrigin
@RestController
@Service
@RequestMapping("/api/analysis")
public class AnalysisController {
    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/genderCount")
    public int[] getGenderCount() {
        int[] genderCounts = new int[2];
        genderCounts[0] = userRepository.countByGender("男");
        genderCounts[1] = userRepository.countByGender("女");
        return genderCounts;
    }

    @GetMapping("/educationData")
    public int[] educationData() {
        int[] educationCounts = new int[5];
        educationCounts[0] = userRepository.countByEducation("小学");
        educationCounts[1] = userRepository.countByEducation("初中");
        educationCounts[2] = userRepository.countByEducation("高中");
        educationCounts[3] = userRepository.countByEducation("大学");
        educationCounts[4] = userRepository.countByEducation("研究生");
        return educationCounts;
    }

    @GetMapping("/ageCounts")
    public int[] getAgeCounts() {
        LocalDate startDate1 = LocalDate.of(2013, 1, 1);
        LocalDate startDate2 = LocalDate.of(2005, 1, 1);
        LocalDate endDate2 = LocalDate.of(2013, 12, 31);
        LocalDate startDate3 = LocalDate.of(1993, 1, 1);
        LocalDate endDate3 = LocalDate.of(2005, 12, 31);
        LocalDate startDate4 = LocalDate.of(1963, 1, 1);
        LocalDate endDate4 = LocalDate.of(1993, 12, 31);
        LocalDate endDate5 = LocalDate.of(1963, 12, 31);

        int count1 = userRepository.countByBirthdayAfter(startDate1);
        int count2 = userRepository.countByBirthdayBetween(startDate2, endDate2);
        int count3 = userRepository.countByBirthdayBetween(startDate3, endDate3);
        int count4 = userRepository.countByBirthdayBetween(startDate4, endDate4);
        int count5 = userRepository.countByBirthdayBefore(endDate5);

        return new int[]{count1, count2, count3, count4, count5};
    }
}
