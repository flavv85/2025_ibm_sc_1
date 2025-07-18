package com.example.spring_boot_tutorial.Bapplication.coaches;

import com.example.spring_boot_tutorial.Ddomain.coach.Coach;
import com.example.spring_boot_tutorial.Ddomain.coach.Coaches;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DeleteCoach {
    Coaches coaches;

    public void deleteCoach(String coachId) {
        Coach coach = coaches.getCoachById(coachId).orElseThrow(UnknownError::new);
        coaches.deleteCoach(coach);
    }
}
