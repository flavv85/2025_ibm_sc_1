package com.example.spring_boot_tutorial.Aexposition.controller;

import com.example.spring_boot_tutorial.Aexposition.dto.CreateUpdateReviewDto;
import com.example.spring_boot_tutorial.Aexposition.dto.ReviewDto;
import com.example.spring_boot_tutorial.Aexposition.mapper.ReviewMapperService;
import com.example.spring_boot_tutorial.Bapplication.Review.ConsultAllReviews;
import com.example.spring_boot_tutorial.Bapplication.Review.CreateReview;
import com.example.spring_boot_tutorial.Bapplication.Review.DeleteReview;
import com.example.spring_boot_tutorial.Ddomain.Review.Review;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
@AllArgsConstructor
public class ReviewController {

    CreateReview createReview;
    DeleteReview deleteReview;
    ConsultAllReviews consultAllReviews;
    ReviewMapperService reviewMapperService;

    @GetMapping
    public ResponseEntity<List<ReviewDto>>  getAllReviews() {
        List<Review> reviews = consultAllReviews.consultAll();
        List<ReviewDto> reviewDtos = reviews.stream()
                .map(review -> reviewMapperService.mapFromDomainToDto(review))
                .toList();
        return ResponseEntity.ok(reviewDtos);
    }
    @PostMapping
    public ResponseEntity<Void> createReview(@RequestBody CreateUpdateReviewDto reviewDto) {
        Review reviewToBeAdded = reviewMapperService.mapDtoToDomain(reviewDto);
        createReview.save(reviewToBeAdded);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping
    public ResponseEntity<String> deleteReview(@RequestParam String reviewId) {
        deleteReview.delete(reviewId);
        return ResponseEntity.ok("Review delete successfully");
    }



}
