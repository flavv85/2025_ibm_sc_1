package com.example.spring_boot_tutorial.Bapplication.members;

import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClasses;
import com.example.spring_boot_tutorial.Ddomain.member.Member;
import com.example.spring_boot_tutorial.Ddomain.member.Members;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConsultAllMembersFromFitnessClass {

    @Autowired
    FitnessClasses fitnessClasses;

    public List<Member> getAllMembersFromFitnessClass(String fitnessClassId) {
        return fitnessClasses.getAllMembersFromFitnessClass(fitnessClassId);
    }

}
