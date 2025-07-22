package com.example.spring_boot_tutorial.Cinfrastructure.repository;

import com.example.spring_boot_tutorial.Ddomain.Review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,String> {
}
