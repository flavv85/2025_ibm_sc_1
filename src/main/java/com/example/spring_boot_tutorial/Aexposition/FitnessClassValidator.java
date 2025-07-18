package com.example.spring_boot_tutorial.Aexposition;

import com.example.spring_boot_tutorial.Ddomain.Exception.BussinessException;
import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClass;
import com.example.spring_boot_tutorial.Ddomain.member.Member;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class FitnessClassValidator {
    public boolean isMemberCountValid(Set<Member> members) {
        int count = members.size();
        return count >= 3 && count <= 8;
    }
    public void validateFitnessClass(FitnessClass fitnessClass) {
        if (fitnessClass.getCoach() == null || fitnessClass.getCoach().getId() == null) {
            throw new BussinessException(String.format("Coach is missing or invalid."));
        }
        if (fitnessClass.getStartTime() == null || fitnessClass.getEndTime() == null) {
            throw new BussinessException(String.format("Start time or end time cannot be null."));
        }
        if (!fitnessClass.getStartTime().isBefore(fitnessClass.getEndTime())) {
            throw new BussinessException(String.format("Start time must be before end time."));
        }
    }
}
