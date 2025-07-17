package com.example.spring_boot_tutorial.Cinfrastructure.repository;


import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FitnessClassRepository extends JpaRepository<FitnessClass, String> {
    List<FitnessClass> findByCoach_Id(String coachId);
}
