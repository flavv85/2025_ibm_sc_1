package com.example.spring_boot_tutorial.Ddomain.member;

import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClass;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface Members {
    List<Member> getAllMembers();

    Optional<Member> getMemberById(String memberId);

    Optional<Set<Member>> GetMemberByFitnessClass(FitnessClass fitnessClass);

    void CreateOrUpdate(Member member);

    void DeleteMember(Member member);
}
