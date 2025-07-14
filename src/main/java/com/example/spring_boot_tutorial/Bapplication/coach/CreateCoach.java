package com.example.spring_boot_tutorial.Bapplication.coach;

import com.example.spring_boot_tutorial.Ddomain.coach.Coach;
import com.example.spring_boot_tutorial.Ddomain.coach.Coaches;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CreateCoach {

    Coaches coaches;

        public void createCoach(Coach coach) {
            if (coach.getName() == null || coach.getName().isBlank()) {
                throw new IllegalArgumentException("Coach name is required");
            }

            coach.setId(UUID.randomUUID().toString());
            coaches.saveCoach(coach);
        }
}
