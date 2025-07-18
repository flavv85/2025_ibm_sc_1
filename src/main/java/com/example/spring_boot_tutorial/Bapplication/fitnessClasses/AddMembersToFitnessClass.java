package com.example.spring_boot_tutorial.Bapplication.fitnessClasses;

import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClass;
import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClasses;
import com.example.spring_boot_tutorial.Ddomain.member.Member;
import com.example.spring_boot_tutorial.Ddomain.member.Members;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AddMembersToFitnessClass {


    ConsultFitnessClassById consultFitnessClassById;
    FitnessClasses fitnessClasses;
    Members members;

    public void addMembers(String fitnessClassId, List<String> memberIds) {
        FitnessClass fitnessClass = consultFitnessClassById.consult(fitnessClassId);
        Set<Member> memberSet = new HashSet<>();
        memberIds.forEach(v -> {
            Optional<Member> member = members.getMemberById(v);
            member.ifPresent(memberSet::add);
        });
        //TODO add a validation if all members exist by id if not don't stop execution, just log the error

        fitnessClass.setMembers(memberSet);
        fitnessClasses.updateFitnessClass(fitnessClass);
    }

}