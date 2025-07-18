package com.example.spring_boot_tutorial.Aexposition.controller;

import com.example.spring_boot_tutorial.Aexposition.dto.CreateUpdateMemberDto;
import com.example.spring_boot_tutorial.Aexposition.dto.MemberDto;
import com.example.spring_boot_tutorial.Aexposition.mapper.MemberMapperService;
import com.example.spring_boot_tutorial.Bapplication.members.ConsultAllMembers;
import com.example.spring_boot_tutorial.Bapplication.members.CreateMember;
import com.example.spring_boot_tutorial.Bapplication.members.DeleteMember;
import com.example.spring_boot_tutorial.Bapplication.members.UpdateMember;
import com.example.spring_boot_tutorial.Ddomain.member.Member;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    ConsultAllMembers consultAllMembers;
    MemberMapperService memberMapperService;
    CreateMember createMember;
    DeleteMember deleteMember;
    UpdateMember updateMember;

    @GetMapping
    public ResponseEntity<List<MemberDto>> consultAllMembers() {
        List<Member> membersList = consultAllMembers.consultAll();
        List<MemberDto> response = membersList
                .stream()
                .map(member -> memberMapperService.mapMemberFromEntityToDto(member))
                .toList();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Void> createMember(@RequestBody CreateUpdateMemberDto memberDto) {
        createMember.createMember(memberMapperService.mapMemberFromDtoToEntity(memberDto));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping
    public ResponseEntity<String> deleteMember(@RequestParam String id) {
        deleteMember.deleteMember(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> updateMember(@RequestBody CreateUpdateMemberDto memberDto) {
        updateMember.UpdateMember(memberMapperService.mapMemberFromDtoToEntity(memberDto));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
