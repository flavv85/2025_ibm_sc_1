package com.example.spring_boot_tutorial.Ddomain.coach;

import java.util.List;
import java.util.Optional;

public interface Coaches {
    List<Coach> getAllCoaches();
    Optional<Coach> getCoachById (String coachId);
}
