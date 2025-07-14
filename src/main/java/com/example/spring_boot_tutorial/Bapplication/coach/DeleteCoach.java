package com.example.spring_boot_tutorial.Bapplication.coach;

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

    public void delete(String coach_id) {
        Coach coachToBeDeleted = coaches.getCoachById(coach_id).orElseThrow(UnknownError::new);
        coaches.deleteCoach(coachToBeDeleted);
    }
}
