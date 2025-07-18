package com.example.spring_boot_tutorial.Cinfrastructure.repository.implementation;

import com.example.spring_boot_tutorial.Cinfrastructure.repository.FitnessClassRepository;
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
    FitnessClassRepository repository;

    @Override
    public List<FitnessClass> getAllFitnessClasses() {
        return repository.findAll();
    }

    @Override
    public void addFitnessClass(FitnessClass fitnessClass) {
        repository.save(fitnessClass);
    }

    @Override
    public void updateFitnessClass(FitnessClass fitnessClass) {
        repository.save(fitnessClass);
    }

    @Override
    public Optional<FitnessClass> getById(String id) { return repository.findById(id); }

    @Override
    public void delete(FitnessClass fitnessClass) { repository.delete(fitnessClass); }
}
