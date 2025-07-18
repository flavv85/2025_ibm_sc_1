package com.example.spring_boot_tutorial.Bapplication.fitnessClass;

import com.example.spring_boot_tutorial.Ddomain.coach.Coaches;
import com.example.spring_boot_tutorial.Ddomain.exceptions.CoachNotFoundException;
import com.example.spring_boot_tutorial.Ddomain.exceptions.InvalidMembersCountException;
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
public class CreateFitnessClass {
    FitnessClasses fitnessClasses;
    Coaches coaches;
    public void createFitnessClass(FitnessClass fitnessClass) {
        validate(fitnessClass);

        //todo validare please check if the associated coach really exists in the db, if not throw an error
        //todo validate if no if members is < 3 or > 8 throw an error saying that it needs to be between 3 and 8 members
        fitnessClasses.saveFitnessClass(fitnessClass);
    }
    private void validate(FitnessClass fitnessClass){
        boolean coachExists = coaches.getCoachById(fitnessClass.getCoach().getId()).isPresent();
        if(!coachExists){
            throw new CoachNotFoundException(fitnessClass.getCoach().getId());
        }
        int size=fitnessClass.getMembers() !=null
                ?fitnessClass.getMembers().size()
                :0;
        if (size < 3 || size > 8) {

            throw new InvalidMembersCountException(size);

        }
    }
}