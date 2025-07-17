package com.example.spring_boot_tutorial.Bapplication.Review;

import com.example.spring_boot_tutorial.Ddomain.Review.Review;
import com.example.spring_boot_tutorial.Ddomain.Review.Reviews;
import com.example.spring_boot_tutorial.Ddomain.member.MemberStatus;
import com.example.spring_boot_tutorial.Ddomain.member.Members;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CreateReview {

    Reviews reviews;
    Members members;

    public void save(Review review) {
        reviews.saveReview(review);

        members.getMemberById(review.getMemberId()).ifPresent(member -> {
                member.setStatus(MemberStatus.ACTIVE);
                members.saveMember(member);
        });
    }
}
