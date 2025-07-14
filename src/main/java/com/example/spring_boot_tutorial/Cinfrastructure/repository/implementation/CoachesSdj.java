package com.example.spring_boot_tutorial.Cinfrastructure.repository.implementation;

import com.example.spring_boot_tutorial.Cinfrastructure.repository.CoachRepository;
import com.example.spring_boot_tutorial.Ddomain.coach.Coach;
import com.example.spring_boot_tutorial.Ddomain.coach.Coaches;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CoachesSdj implements Coaches {

    CoachRepository coachRepository;

    @Override
    public List<Coach> getAllCoaches() {
        return coachRepository.findAll();
    }
}
