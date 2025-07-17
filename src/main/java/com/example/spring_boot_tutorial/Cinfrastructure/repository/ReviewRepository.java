package com.example.spring_boot_tutorial.Cinfrastructure.repository;

import com.example.spring_boot_tutorial.Ddomain.Review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, String> {

    boolean existsByMemberId(String memberId);

    @Query("SELECT r.coachId FROM Review r GROUP BY r.coachId HAVING AVG(r.mark) >= :minMark")
    List<String> findCoachIdsWithAverageMarkAbove(@Param("minMark") int minMark);
}
