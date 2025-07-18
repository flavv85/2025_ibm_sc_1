package com.example.spring_boot_tutorial.Bapplication.coach;

import com.example.spring_boot_tutorial.Ddomain.coach.Coach;
import com.example.spring_boot_tutorial.Ddomain.coach.Coaches;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DeleteCoach {

    Coaches coaches;

    public void delete(String coachId) {
        Coach coachToBeDeleted = coaches.getCoachById(coachId).orElseThrow(UnknownError::new);
        coaches.deleteCoach(coachToBeDeleted);
    }
}
