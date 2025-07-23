package com.example.spring_boot_tutorial.Aexposition.controller;

import com.example.spring_boot_tutorial.Aexposition.dto.ConsultCoachDto;
import com.example.spring_boot_tutorial.Aexposition.dto.ConsultReviewDTO;
import com.example.spring_boot_tutorial.Aexposition.dto.CreateUpdateReviewDTO;
import com.example.spring_boot_tutorial.Aexposition.mapper.CoachMapperService;
import com.example.spring_boot_tutorial.Aexposition.mapper.ReviewMapperService;
import com.example.spring_boot_tutorial.Bapplication.Review.ConsultAllReviews;
import com.example.spring_boot_tutorial.Bapplication.Review.ConsultCoachesWithGrade;
import com.example.spring_boot_tutorial.Bapplication.Review.CreateUpdateReview;
import com.example.spring_boot_tutorial.Ddomain.Review.Review;
import com.example.spring_boot_tutorial.Ddomain.coach.Coach;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {
    ConsultAllReviews consultAllReviews;
    CreateUpdateReview createUpdateReview;
    ReviewMapperService reviewMapperService;
    ConsultCoachesWithGrade consultCoachesWithGrade;
    CoachMapperService coachMapperService;
    @GetMapping
    public ResponseEntity<List<ConsultReviewDTO>>getAllReviews(){
        List<Review>reviews=consultAllReviews.getAllReviews();
        List<ConsultReviewDTO> reviewsDto=reviews.stream().map(reviewMapperService::mapFromDomain).toList();
        return new ResponseEntity<>(reviewsDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createReview(@RequestBody CreateUpdateReviewDTO reviewDTO) {
        Review review=reviewMapperService.mapToEntity(reviewDTO,null);
        createUpdateReview.createReview(review);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping(value = "/grade>8")
    public ResponseEntity<List<ConsultCoachDto>> getAllReviewsByDate() {
        Set<Coach> coaches= consultCoachesWithGrade.getCoaches();
        List<ConsultCoachDto> coachesDto=coaches.stream().toList().stream().map(coachMapperService::mapFromDomain).toList();
        return new ResponseEntity<>(coachesDto, HttpStatus.OK);
    }

}
