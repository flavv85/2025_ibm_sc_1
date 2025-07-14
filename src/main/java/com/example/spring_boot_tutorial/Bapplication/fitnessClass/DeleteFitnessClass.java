package com.example.spring_boot_tutorial.Bapplication.fitnessClass;

import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClass;
import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClasses;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DeleteFitnessClass {

    FitnessClasses fitnessClasses;

    public void delete(String fitness_class_id) {
        FitnessClass fitnessClassToBeDeleted = fitnessClasses.getById(fitness_class_id).orElseThrow(UnknownError::new);
        fitnessClasses.delete(fitnessClassToBeDeleted);
    }
}
