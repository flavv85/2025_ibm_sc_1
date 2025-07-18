package com.example.spring_boot_tutorial.Bapplication.fitnessclass;

import com.example.spring_boot_tutorial.Aexposition.FitnessClassValidator;
import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClass;
import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClasses;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CreateFitnessClass {
    FitnessClasses fitnessClasses;
    FitnessClassValidator fitnessClassValidator;

    public void createFitnessClass(FitnessClass fitnessClass) {
        //todo validare please check if the associated coach really exists in the db, if not throw an error
        //todo validate if no if members is < 3 or > 8 throw an error saying that it needs to be between 3 and 8 members
        fitnessClassValidator.validateFitnessClass(fitnessClass);
        fitnessClasses.saveFitnessClass(fitnessClass);
    }
}
