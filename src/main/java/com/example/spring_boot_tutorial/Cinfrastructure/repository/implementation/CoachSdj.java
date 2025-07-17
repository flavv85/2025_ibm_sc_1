package com.example.spring_boot_tutorial.Cinfrastructure.repository.implementation;

import com.example.spring_boot_tutorial.Cinfrastructure.repository.CoachRepository;
import com.example.spring_boot_tutorial.Cinfrastructure.repository.ReviewRepository;
import com.example.spring_boot_tutorial.Ddomain.coach.Coach;
import com.example.spring_boot_tutorial.Ddomain.coach.Coaches;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CoachSdj implements Coaches{

    ReviewRepository reviewRepository;
    CoachRepository repository;

    @Override
    public List<Coach> getAllCoaches(){return repository.findAll();}

    @Override
    public Optional<Coach> getCoachById(String coachId) {
        return repository.findById(coachId);
    }

    @Override
    public void saveCoach(Coach coach) {
        repository.save(coach);
    }

    @Override
    public void delete(Coach coach) {
        repository.delete(coach);
    }

    @Override
    public List<Coach> getCoachesWithAverageMarkAbove(int mark) {
        List<String> coachIds = reviewRepository.findCoachIdsWithAverageMarkAbove(mark);
        return coachIds.stream()
                .map(repository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }


}
