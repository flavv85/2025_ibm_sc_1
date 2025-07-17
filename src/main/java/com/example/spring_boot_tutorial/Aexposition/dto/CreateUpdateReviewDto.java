package com.example.spring_boot_tutorial.Aexposition.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateUpdateReviewDto {
    String id;
    String coachId;
    String memberId;
    Float mark;
}
