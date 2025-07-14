package com.example.spring_boot_tutorial.Aexposition.controller;

import com.example.spring_boot_tutorial.Aexposition.dto.MemberDto;
import com.example.spring_boot_tutorial.Aexposition.mapper.MemberMapperService;
import com.example.spring_boot_tutorial.Bapplication.members.ConsultAllMembers;
import com.example.spring_boot_tutorial.Ddomain.member.Member;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    ConsultAllMembers consultAllMembers;
    MemberMapperService memberMapperService;

    @GetMapping
    public ResponseEntity<List<MemberDto>> consultAllMembers() {
        List<Member> membersList = consultAllMembers.consultAll();
        List<MemberDto> response = membersList
                .stream()
                .map(member -> memberMapperService.mapMemberFromEntityToDto(member))
                .toList();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
