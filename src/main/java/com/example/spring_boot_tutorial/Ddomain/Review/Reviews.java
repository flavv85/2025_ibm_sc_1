package com.example.spring_boot_tutorial.Ddomain.Review;

import com.example.spring_boot_tutorial.Ddomain.coach.Coach;

import java.util.List;
import java.util.Set;

public interface Reviews {
    List<Review> getAllReviews();

    Set<Coach> getCoachesWithMarkBetterThan8();

    void createReview(Review review);
}
