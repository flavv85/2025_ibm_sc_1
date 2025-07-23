package com.example.spring_boot_tutorial.Aexposition.mapper;

import com.example.spring_boot_tutorial.Aexposition.dto.ConsultReviewDTO;
import com.example.spring_boot_tutorial.Aexposition.dto.CreateUpdateReviewDTO;
import com.example.spring_boot_tutorial.Cinfrastructure.repository.CoachRepository;
import com.example.spring_boot_tutorial.Cinfrastructure.repository.MemberRepository;
import com.example.spring_boot_tutorial.Ddomain.Review.Review;
import com.example.spring_boot_tutorial.Ddomain.coach.Coach;
import com.example.spring_boot_tutorial.Ddomain.member.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReviewMapperService {

    CoachRepository coachRepository;
    MemberRepository memberRepository;

    public ConsultReviewDTO mapFromDomain(Review review) {
        return ConsultReviewDTO.builder()
                .id(review.getId())
                .coach_id(review.getCoach().getId())
                .member_id(review.getMember().getId())
                .mark(review.getMark())
                .build();
    }

    public Review mapToEntity(CreateUpdateReviewDTO dto, String reviewId) {
        Review review = new Review();
        review.setId(StringUtils.hasText(reviewId) ? reviewId : UUID.randomUUID().toString());
        review.setMark(dto.getMark());

        if (StringUtils.hasText(dto.getCoach_id())) {
            Coach coach = coachRepository.findById(dto.getCoach_id()).orElse(null);
            review.setCoach(coach);
        }

        if (StringUtils.hasText(dto.getMember_id())) {
            Member member = memberRepository.findById(dto.getMember_id()).orElse(null);
            review.setMember(member);
        }

        return review;
    }
}
