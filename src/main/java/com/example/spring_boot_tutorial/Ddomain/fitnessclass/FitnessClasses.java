package com.example.spring_boot_tutorial.Ddomain.fitnessclass;

import java.util.List;
import java.util.Optional;

public interface FitnessClasses {
    List<FitnessClass> getFitnessClasses();

    void saveFitnessClass (FitnessClass fitnessClass);

}