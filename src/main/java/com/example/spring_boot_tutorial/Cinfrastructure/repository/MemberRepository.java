package com.example.spring_boot_tutorial.Cinfrastructure.repository;

import com.example.spring_boot_tutorial.Ddomain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
