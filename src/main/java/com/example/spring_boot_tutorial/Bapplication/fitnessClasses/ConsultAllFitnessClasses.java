package com.example.spring_boot_tutorial.Bapplication.fitnessClasses;

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
public class ConsultAllFitnessClasses {

    FitnessClasses fitnessClasses;

    public List<FitnessClass> consultAll() {
        return fitnessClasses.getAllFitnessClasses();
    }
}
