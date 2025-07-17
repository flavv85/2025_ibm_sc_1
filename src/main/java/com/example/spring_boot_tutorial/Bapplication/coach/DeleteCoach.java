package com.example.spring_boot_tutorial.Bapplication.coach;

import com.example.spring_boot_tutorial.Ddomain.coach.Coach;
import com.example.spring_boot_tutorial.Ddomain.coach.Coaches;
import com.example.spring_boot_tutorial.Ddomain.coach.Exception.CoachDeletionException;
import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClass;
import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClasses;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DeleteCoach {
    Coaches coaches;
    FitnessClasses fitnessClasses;

    public void deleteCoach(String id) {
        List<FitnessClass> classes = fitnessClasses.findByCoach_Id(id);
        if (!classes.isEmpty()) {
            throw new CoachDeletionException("Cannot delete coach with id " + id + " because they have assigned fitness classes.");
        }
        Coach coachToBeDeleted = coaches.getCoachById(id).orElseThrow(UnknownError::new);
        coaches.delete(coachToBeDeleted);
    }
}
