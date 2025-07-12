package com.example.spring_boot_tutorial.Cinfrastructure.repository.implementation;

import com.example.spring_boot_tutorial.Cinfrastructure.repository.FitnessClassRepository;
import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClass;
import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClasses;
import com.example.spring_boot_tutorial.Ddomain.member.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FitnessClassSdj implements FitnessClasses {

    @Autowired
    FitnessClassRepository repository;

    @Override
    public List<FitnessClass> getAllFitnessClasses(){return repository.findAll();}

    @Override
    public List<Member> getAllMembersFromFitnessClass(String fitnessClassId){
        return repository.findById(fitnessClassId)
                .map(fitnessClass -> new ArrayList<>(fitnessClass.getMembers()))
                .orElseThrow(()-> new RuntimeException("Fitness Class not found"));
    }
}
