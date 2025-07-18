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
    UpdateMember updateMember;
    DeleteMember deleteMember;

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
    public ResponseEntity<Void> create(CreateUpdateMemberDto dto) {
        Member memberToBeSaved = memberMapperService.mapFromDtoToEntity(dto);
        createMember.createMember(memberToBeSaved);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestParam String memberId,
                                       @RequestBody CreateUpdateMemberDto dto) {
        Member memberToBeUpdated = memberMapperService.mapFromDtoToEntity(dto);
        updateMember.updateMember(memberToBeUpdated, memberId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam String memberId) {
        deleteMember.deleteMember(memberId);
        return ResponseEntity.ok("Member deleted successfully");
    }
}
