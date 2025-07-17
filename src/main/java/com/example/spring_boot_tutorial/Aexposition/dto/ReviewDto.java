package com.example.spring_boot_tutorial.Aexposition.dto;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class ReviewDto {
    CoachDto coach;
    MemberDto member;
    Float mark;
}
