package com.example.spring_boot_tutorial.Bapplication.fitnessclass;

import com.example.spring_boot_tutorial.Cinfrastructure.repository.FitnessClassRepository;
import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClass;
import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClasses;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConsultAllFitnessClasses {
        @Autowired
        FitnessClasses fitnessclasses;

        public List<FitnessClass> consultAll() {return fitnessclasses.getAllFitnessClasses();}

}
