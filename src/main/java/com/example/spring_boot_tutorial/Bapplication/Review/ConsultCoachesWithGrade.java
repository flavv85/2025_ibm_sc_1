package com.example.spring_boot_tutorial.Bapplication.Review;

import com.example.spring_boot_tutorial.Ddomain.Review.Reviews;
import com.example.spring_boot_tutorial.Ddomain.coach.Coach;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConsultCoachesWithGrade {
    @Autowired
    Reviews reviews;
    public Set<Coach> getCoaches(){
        return  reviews.getCoachesWithMarkBetterThan8();
    }
}
