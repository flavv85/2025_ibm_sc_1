package com.example.spring_boot_tutorial.Bapplication.fitnessClasses;

import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClass;
import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClasses;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConsultFitnessClassById {

    FitnessClasses fitnessClasses;

    public FitnessClass consult(String id) {
        return fitnessClasses.getById(id).orElseThrow(UnknownError::new);
    }

}
