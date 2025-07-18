package com.example.spring_boot_tutorial.Bapplication.fitnessclass;

import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClass;
import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClasses;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UpdateFitnessClass {

    FitnessClasses fitnessClasses;

    public void update(FitnessClass fitnessClass) {
        validateFitnessClass(fitnessClass);
        fitnessClasses.updateFitnessClass(fitnessClass);
    }

    private void validateFitnessClass(FitnessClass fitnessClass) {
        if (fitnessClass.getCoach() == null || fitnessClass.getCoach().getId() == null) {
            throw new IllegalArgumentException("Coach is missing or invalid.");
        }
        if (fitnessClass.getStartTime() == null || fitnessClass.getEndTime() == null) {
            throw new IllegalArgumentException("Start time or end time cannot be null.");
        }
        if (!fitnessClass.getStartTime().isBefore(fitnessClass.getEndTime())) {
            throw new IllegalArgumentException("Start time must be before end time.");
        }
    }

}
