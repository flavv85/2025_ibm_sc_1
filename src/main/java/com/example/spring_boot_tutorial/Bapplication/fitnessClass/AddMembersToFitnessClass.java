package com.example.spring_boot_tutorial.Bapplication.fitnessClass;

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
        List<String> missingIds = new ArrayList<>();

        for (String memberId : memberIds) {
            Optional<Member> optionalMember = members.getMemberById(memberId);
            if (optionalMember.isPresent()) {
                memberSet.add(optionalMember.get());
            } else {
                missingIds.add(memberId);
                log.warn("Member with ID [{}] does not exist.", memberId);
            }
        }



        fitnessClass.setMembers(memberSet);
        fitnessClasses.updateFitnessClass(fitnessClass);
    }

}
