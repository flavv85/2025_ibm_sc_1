package com.example.spring_boot_tutorial.Ddomain.Review;


import java.util.List;
import java.util.Optional;

public interface Reviews {

    List<Review> getAllReviews();

    Optional<Review> getReviewById(String reviewId);

    void delete(Review review);
    void saveReview(Review review);

    boolean existsByMemberId(String memberId);
}
