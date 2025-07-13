package com.example.spring_boot_tutorial.Cinfrastructure.repository.implementation;

import com.example.spring_boot_tutorial.Cinfrastructure.repository.MemberRepository;
import com.example.spring_boot_tutorial.Ddomain.member.Members;
import com.example.spring_boot_tutorial.Ddomain.member.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MembersSdj implements Members {

    MemberRepository repository;

    @Override
    public List<Member> getAllMembers(){
        return repository.findAll();
    }
}
