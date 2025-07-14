package com.example.spring_boot_tutorial.Bapplication.members;

import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClass;
import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClasses;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConsultAllMembersFromFitnessClass {

    @Autowired
    FitnessClasses fitnessClasses;

    public Optional<FitnessClass> consultAllMembersFromFitnessClass(String fitnessClassId) {
        return fitnessClasses.getFitnessClassById(fitnessClassId);
    }

}
