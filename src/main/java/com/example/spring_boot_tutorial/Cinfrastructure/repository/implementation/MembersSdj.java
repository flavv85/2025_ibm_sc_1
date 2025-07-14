package com.example.spring_boot_tutorial.Cinfrastructure.repository.implementation;

import com.example.spring_boot_tutorial.Cinfrastructure.repository.MemberRepository;
import com.example.spring_boot_tutorial.Ddomain.member.Member;
import com.example.spring_boot_tutorial.Ddomain.member.Members;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MembersSdj implements Members {

    @Autowired
    MemberRepository repository;

    @Override
    public List<Member> getAllMembers() {
      return repository.findAll();
    }

    @Override
    public Optional<Member> getMemberById(String memberId) {
        return repository.findById(memberId);
    }
}
