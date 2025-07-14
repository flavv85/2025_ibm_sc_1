package com.example.spring_boot_tutorial.Ddomain.member;

import java.util.List;
import java.util.Optional;

public interface Members {
    List<Member> getAllMembers();

    Optional<Member> getMemberById(String memberId);
}
