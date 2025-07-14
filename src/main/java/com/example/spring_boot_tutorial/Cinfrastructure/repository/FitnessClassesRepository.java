package com.example.spring_boot_tutorial.Cinfrastructure.repository;

import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FitnessClassesRepository extends JpaRepository<FitnessClass, String> {
}
