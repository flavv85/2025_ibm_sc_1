package com.example.spring_boot_tutorial.Cinfrastructure.repository.implementation;

import com.example.spring_boot_tutorial.Cinfrastructure.repository.ReviewRepository;
import com.example.spring_boot_tutorial.Ddomain.Review.Review;
import com.example.spring_boot_tutorial.Ddomain.Review.Reviews;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReviewSdj implements Reviews {

    ReviewRepository repository;

    @Override
    public List<Review> getAllReviews() {
        return repository.findAll();
    }

    @Override
    public Optional<Review> getReviewById(String id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Review review) {
        repository.delete(review);
    }

    @Override
    public void saveReview(Review review) {
        repository.save(review);
    }

}
