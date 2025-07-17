package com.example.spring_boot_tutorial.Aexposition;

import com.example.spring_boot_tutorial.Ddomain.member.Member;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class FitnessClassValidator {
    public boolean isMemberCountValid(Set<Member> members) {
        int count = members.size();
        return count >= 3 && count <= 8;
    }
}
