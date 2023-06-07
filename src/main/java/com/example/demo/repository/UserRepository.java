package com.example.demo.repository;

import com.example.demo.entity.Domicile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface UserRepository extends JpaRepository<Domicile, Integer> {
    int countByBirthdayAfter(LocalDate date);

    int countByBirthdayBetween(LocalDate startDate, LocalDate endDate);

    int countByBirthdayBefore(LocalDate date);

    int countByGender(String gender);

    int countByEducation(String education);
}
