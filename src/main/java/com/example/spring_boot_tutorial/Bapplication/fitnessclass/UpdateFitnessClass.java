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
public class UpdateFitnessClass {

    FitnessClasses fitnessClasses;
    FitnessClassValidator fitnessClassValidator;

    public void update(FitnessClass fitnessClass) {
        fitnessClassValidator.validateFitnessClass(fitnessClass);
        fitnessClasses.updateFitnessClass(fitnessClass);
    }



}
