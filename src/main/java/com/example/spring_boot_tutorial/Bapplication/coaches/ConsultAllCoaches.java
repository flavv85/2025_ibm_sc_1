package com.example.spring_boot_tutorial.Bapplication.coaches;

import com.example.spring_boot_tutorial.Ddomain.coach.Coach;
import com.example.spring_boot_tutorial.Ddomain.coach.Coaches;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConsultAllCoaches {

    Coaches coaches;

    public List<Coach> consultAllCoaches(){
        return coaches.consultAllCoaches();
    }
}
