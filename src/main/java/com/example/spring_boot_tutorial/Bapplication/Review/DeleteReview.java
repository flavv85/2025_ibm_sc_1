package com.example.spring_boot_tutorial.Bapplication.Review;

import com.example.spring_boot_tutorial.Ddomain.Review.Review;
import com.example.spring_boot_tutorial.Ddomain.Review.Reviews;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DeleteReview {

    Reviews reviews;

    public void delete(String reviewId) {
        Review reviewToBeDeleted = reviews.getReviewById(reviewId).orElseThrow(UnknownError::new);
        reviews.delete(reviewToBeDeleted);
    }
}
