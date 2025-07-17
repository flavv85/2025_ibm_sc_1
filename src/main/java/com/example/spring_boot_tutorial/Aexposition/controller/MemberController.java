package com.example.spring_boot_tutorial.Aexposition.controller;

import com.example.spring_boot_tutorial.Aexposition.dto.CreateUpdateMemberDto;
import com.example.spring_boot_tutorial.Aexposition.dto.MemberDto;
import com.example.spring_boot_tutorial.Aexposition.mapper.MemberMapperService;
import com.example.spring_boot_tutorial.Bapplication.Review.ExistsReview;
import com.example.spring_boot_tutorial.Bapplication.fitnessclass.CreateFitnessClass;
import com.example.spring_boot_tutorial.Bapplication.members.ConsultAllMembers;
import com.example.spring_boot_tutorial.Bapplication.members.ConsultAllMembersFromFitnessClass;
import com.example.spring_boot_tutorial.Bapplication.members.CreateMember;
import com.example.spring_boot_tutorial.Bapplication.members.DeleteMember;
import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClass;
import com.example.spring_boot_tutorial.Ddomain.member.Member;
import com.example.spring_boot_tutorial.Ddomain.member.MemberStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/member")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MemberController {

    ExistsReview existsReview;
    ConsultAllMembers consultAllMembers;
    ConsultAllMembersFromFitnessClass consultAllMembersFromFitnessClass;
    CreateMember createMember;
    DeleteMember deleteMember;
    CreateFitnessClass createFitnessClass;
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

    @PostMapping
    public ResponseEntity<Void> consultMember(@RequestBody CreateUpdateMemberDto dto) {

        Member memberToBeSaved = memberMapperService.mapDtoToEntityFromDto(dto);
        createMember.createMember(memberToBeSaved);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteMember(@RequestParam String id) {
        deleteMember.deleteMember(id);
        return ResponseEntity.ok("Member deleted successfully");
    }

    @DeleteMapping("/fitness-class")
    public ResponseEntity<String> deleteAllMembersFromFitnessClass(@RequestParam String fitnessClassId) {
        Optional<FitnessClass> fitnessClassOpt = consultAllMembersFromFitnessClass.consultAllMembersFromFitnessClass(fitnessClassId);
        if (fitnessClassOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Fitness class with id " + fitnessClassId + " not found.");
        }

        FitnessClass fitnessClass = fitnessClassOpt.get();
        List<Member> membersToProcess = new ArrayList<>(fitnessClass.getMembers());

        for (Member member : membersToProcess) {
            boolean hasActiveReview = existsReview.existsByMemberId(member.getId());
            if (hasActiveReview) {
                member.setStatus(MemberStatus.INACTIVE);
                createMember.createMember(member);
            } else {
                deleteMember.deleteMember(member.getId());
            }
        }

        fitnessClass.getMembers().clear();
        createFitnessClass.createFitnessClass(fitnessClass);

        return ResponseEntity.ok("All applicable members have been removed or marked inactive.");
    }


}
