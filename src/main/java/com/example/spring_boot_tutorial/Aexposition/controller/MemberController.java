package com.example.spring_boot_tutorial.Aexposition.controller;

import com.example.spring_boot_tutorial.Aexposition.dto.MemberDto;
import com.example.spring_boot_tutorial.Aexposition.mapper.MemberMapperService;
import com.example.spring_boot_tutorial.Bapplication.members.ConsultAllMembers;
import com.example.spring_boot_tutorial.Bapplication.members.ConsultAllMembersFromFitnessClass;
import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClass;
import com.example.spring_boot_tutorial.Ddomain.member.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/member")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MemberController {

    ConsultAllMembers consultAllMembers;
    ConsultAllMembersFromFitnessClass consultAllMembersFromFitnessClass;
    MemberMapperService memberMapperService;

    @GetMapping
    public ResponseEntity<List<MemberDto>> consultAllMembers() {
        List<Member> membersList = consultAllMembers.consultAll();
        List<MemberDto> response = membersList
                .stream()
                .map(member -> memberMapperService.mapMemberFromEntityToDto(member, false))
                .toList();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/fitness-class/{fitnessClassId}")
    public ResponseEntity<List<MemberDto>> consultAllMembersFromFitnessClass(@PathVariable String fitnessClassId) {

        Optional<FitnessClass> fitnessClass = consultAllMembersFromFitnessClass.consultAllMembersFromFitnessClass(fitnessClassId);
        if (fitnessClass.isPresent()) {
            List<MemberDto> response = fitnessClass.get().getMembers()
                    .stream()
                    .map(member -> memberMapperService.mapMemberFromEntityToDto(member, true))
                    .toList();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/fitness-class")
    public ResponseEntity<List<MemberDto>> consultAllMembersFromFitnessClassWithRP(@RequestParam String fitnessClassId) {

        Optional<FitnessClass> fitnessClass = consultAllMembersFromFitnessClass.consultAllMembersFromFitnessClass(fitnessClassId);
        if (fitnessClass.isPresent()) {
            List<MemberDto> response = fitnessClass.get().getMembers()
                    .stream()
                    .map(member -> memberMapperService.mapMemberFromEntityToDto(member, false))
                    .toList();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
