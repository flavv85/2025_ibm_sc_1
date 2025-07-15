package com.example.spring_boot_tutorial.Bapplication.fitnessclass;

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

        memberIds.forEach(id -> {
            Optional<Member> member = members.getMemberById(id);
            if (member.isPresent()) {
                memberSet.add(member.get());
            } else {
                System.out.println("Warning: Member with ID " + id + " does not exist.");
            }
        });

        fitnessClass.setMembers(memberSet);
        fitnessClasses.updateFitnessClass(fitnessClass);
    }


}
