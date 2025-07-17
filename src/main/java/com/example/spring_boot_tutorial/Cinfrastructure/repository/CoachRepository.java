package com.example.spring_boot_tutorial.Cinfrastructure.repository;

import com.example.spring_boot_tutorial.Ddomain.coach.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoachRepository extends JpaRepository<Coach, String> {
}
