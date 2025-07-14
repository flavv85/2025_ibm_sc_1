package com.example.spring_boot_tutorial.Cinfrastructure.repository.implementation;


import com.example.spring_boot_tutorial.Cinfrastructure.repository.FitnessClassesRepository;
import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClass;
import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClasses;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FitnessClassesSdj implements FitnessClasses {

    FitnessClassesRepository fitnessClassesRepository;

    @Override
    public List<FitnessClass> getFitnessClasses() {
        return fitnessClassesRepository.findAll();
    }

    @Override
    public void saveFitnessClass(FitnessClass fitnessClass) {
        fitnessClassesRepository.save(fitnessClass);
    }

    @Override
    public void updateFitnessClass(FitnessClass fitnessClass) {
        fitnessClassesRepository.save(fitnessClass);
    }

    @Override
    public Optional<FitnessClass> getById(String id) {
        return fitnessClassesRepository.findById(id);
    }

    @Override
    public void delete(FitnessClass fitnessClass) {
        fitnessClassesRepository.delete(fitnessClass);
    }
}