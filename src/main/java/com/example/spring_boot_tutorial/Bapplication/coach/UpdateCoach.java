package com.example.spring_boot_tutorial.Bapplication.coach;

import com.example.spring_boot_tutorial.Ddomain.coach.Coach;
import com.example.spring_boot_tutorial.Ddomain.coach.Coaches;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE,makeFinal = true)

public class UpdateCoach {
    @Autowired
    Coaches coaches;
    public void update(Coach coach){
        coaches.createOrUpdate(coach);
    }
}
