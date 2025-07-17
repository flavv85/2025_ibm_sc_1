package com.example.spring_boot_tutorial.Ddomain.fitnessclass;

import java.util.List;

public interface FitnessClasses {
    List<FitnessClass> getAllFitnessClasses();

    void addFitnessClass(FitnessClass fitnessClass);
}
