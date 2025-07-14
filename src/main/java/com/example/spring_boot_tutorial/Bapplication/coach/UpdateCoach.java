package com.example.spring_boot_tutorial.Bapplication.coach;

import com.example.spring_boot_tutorial.Aexposition.dto.ConsultCoachDto;
import com.example.spring_boot_tutorial.Ddomain.coach.Coach;
import com.example.spring_boot_tutorial.Ddomain.coach.Coaches;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UpdateCoach {

    Coaches coaches;

    public void updateCoach(String id, Coach coach) {
        Coach existingCoach = coaches.getCoachById(id)
                .orElseThrow(() -> new IllegalArgumentException("Coach not found"));

        existingCoach.setName(coach.getName());

        coaches.saveCoach(existingCoach);
    }
}
