package com.example.spring_boot_tutorial.Bapplication.fitnessClass;

import com.example.spring_boot_tutorial.Ddomain.exceptions.CoachNotFoundException;
import com.example.spring_boot_tutorial.Ddomain.exceptions.FitnessClassNotFoundException;
import com.example.spring_boot_tutorial.Ddomain.exceptions.InvalidTimeRangeException;
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

    public void update(FitnessClass patch, String fitnessClassId) {


        patch.setId(fitnessClassId);


        FitnessClass existing = fitnessClasses.getById(fitnessClassId)
                .orElseThrow(() -> new FitnessClassNotFoundException(fitnessClassId));


        validate(existing, patch);


        if (patch.getName()       != null) existing.setName(patch.getName());
        if (patch.getStartTime()  != null) existing.setStartTime(patch.getStartTime());
        if (patch.getEndTime()    != null) existing.setEndTime(patch.getEndTime());
        if (patch.getCoach()      != null) existing.setCoach(patch.getCoach());
        if (patch.getMembers()    != null) existing.setMembers(patch.getMembers());


        fitnessClasses.updateFitnessClass(existing);
    }
        private void validate(FitnessClass existing,FitnessClass patch) {
            if (patch.getCoach() == null && patch.getCoach() != null) {
                throw new CoachNotFoundException(existing.getCoach().getId());
            }


            if (patch.getStartTime() != null && patch.getEndTime() != null &&
                    !patch.getStartTime().isBefore(patch.getEndTime())) {
                throw new InvalidTimeRangeException();
            }
        }
        }
