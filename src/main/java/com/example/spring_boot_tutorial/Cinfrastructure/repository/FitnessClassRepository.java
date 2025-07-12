package com.example.spring_boot_tutorial.Cinfrastructure.repository;


import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FitnessClassRepository extends JpaRepository<FitnessClass, String> {
    Optional<FitnessClass> findById(String id);
}
