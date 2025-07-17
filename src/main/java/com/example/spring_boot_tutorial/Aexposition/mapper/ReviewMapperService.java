package com.example.spring_boot_tutorial.Aexposition.mapper;

import com.example.spring_boot_tutorial.Aexposition.dto.*;
import com.example.spring_boot_tutorial.Ddomain.Review.Review;
import com.example.spring_boot_tutorial.Ddomain.coach.Coach;
import com.example.spring_boot_tutorial.Ddomain.coach.Coaches;
import com.example.spring_boot_tutorial.Ddomain.member.Member;
import com.example.spring_boot_tutorial.Ddomain.member.Members;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReviewMapperService {

    Members members;
    Coaches coaches;
    MemberMapperService memberMapperService;
    CoachMapperService coachMapperService;



    public ReviewDto mapFromDomainToDto(Review domain) {
        Member member = members.getMemberById(domain.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + domain.getMemberId()));
        Coach coach = coaches.getCoachById(domain.getCoachId())
                .orElseThrow(() -> new IllegalArgumentException("Coach not found with id: " + domain.getCoachId()));

        MemberDto memberDto = memberMapperService.mapMemberFromEntityToDto(member,true);
        CoachDto coachDto = coachMapperService.mapCoachFromEntityToDto(coach);

        return ReviewDto.builder()
                .member(memberDto)
                .coach(coachDto)
                .mark(domain.getMark())
                .build();
    }

    public Review mapDtoToDomain(CreateUpdateReviewDto dto) {
        String id = StringUtils.hasText(dto.getId()) ? dto.getId() : UUID.randomUUID().toString();
        Member member = members.getMemberById(dto.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + dto.getMemberId()));
        Coach coach = coaches.getCoachById(dto.getCoachId())
                .orElseThrow(() -> new IllegalArgumentException("Coach not found with id: " + dto.getCoachId()));

        MemberDto memberDto = memberMapperService.mapMemberFromEntityToDto(member,false);
        CoachDto coachDto = coachMapperService.mapCoachFromEntityToDto(coach);

        return Review.builder()
                .id(id)
                .coachId(coachDto.getId())
                .memberId(memberDto.getId())
                .mark(dto.getMark())
                .build();
    }

}
