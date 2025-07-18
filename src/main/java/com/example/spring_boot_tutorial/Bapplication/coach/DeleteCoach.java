package com.example.spring_boot_tutorial.Bapplication.coach;

import com.example.spring_boot_tutorial.Ddomain.Exception.UnknownObjectException;
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
            throw new CoachDeletionException(String.format("Coach with id %s cannot be deleted because it has %d classes", id, classes.size()));
        }
        Coach coachToBeDeleted = coaches.getCoachById(id).orElseThrow(()->new UnknownObjectException(String.format("Coach with id %s does not exist", id)));
        coaches.delete(coachToBeDeleted);
    }
}
