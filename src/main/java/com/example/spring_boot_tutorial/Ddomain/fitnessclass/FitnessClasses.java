package com.example.spring_boot_tutorial.Ddomain.fitnessclass;

import java.util.List;
import java.util.Optional;

public interface FitnessClasses {
    List<FitnessClass> getFitnessClasses();

    void saveFitnessClass (FitnessClass fitnessClass);

    //could the same method as the one for save, just made it for clarity
    void updateFitnessClass(FitnessClass fitnessClass);
    // todo combine the 2 methods(save and update) and replace with only one method everywhere in the app

    Optional<FitnessClass> getById(String id);

    void delete(FitnessClass fitnessClass);
}