package com.example.spring_boot_tutorial.Ddomain.fitnessclass;


import com.example.spring_boot_tutorial.Ddomain.member.Member;

import java.util.List;
import java.util.Optional;

public interface FitnessClasses {
    List<FitnessClass> getAllFitnessClasses();

    List<Member> getAllMembersFromFitnessClass(String fitnessClassId);
}
