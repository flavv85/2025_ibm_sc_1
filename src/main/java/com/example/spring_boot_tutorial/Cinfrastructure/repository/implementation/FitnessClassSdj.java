package com.example.spring_boot_tutorial.Cinfrastructure.repository.implementation;

import com.example.spring_boot_tutorial.Cinfrastructure.repository.FitnessClassRepository;
import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClass;
import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClasses;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FitnessClassSdj implements FitnessClasses {

    @Autowired
    FitnessClassRepository repository;


    @Override
    public void saveFitnessClass(FitnessClass fitnessClass) {
        repository.save(fitnessClass);
    }

    @Override
    public void updateFitnessClass(FitnessClass fitnessClass) {
        repository.save(fitnessClass);
    }

    @Override
    public Optional<FitnessClass> getById(String id) {
        return repository.findById(id);
    }

    @Override
    public void delete(FitnessClass fitnessClass) {
        repository.delete(fitnessClass);
    }

    @Override
    public List<FitnessClass> getAllFitnessClasses(){return repository.findAll();}

    @Override
    public Optional<FitnessClass> getFitnessClassById(String fitnessClassId) {
        if (!StringUtils.hasText(fitnessClassId)){
            return Optional.empty();
        }
        return repository.findById(fitnessClassId);
    }

//    @Override
//    public List<Member> getAllMembersFromFitnessClass(String fitnessClassId){
//        return repository.findById(fitnessClassId)
//                .map(fitnessClass -> new ArrayList<>(fitnessClass.getMembers()))
//                .orElseThrow(()-> new RuntimeException("Fitness Class not found"));
//    }
}
