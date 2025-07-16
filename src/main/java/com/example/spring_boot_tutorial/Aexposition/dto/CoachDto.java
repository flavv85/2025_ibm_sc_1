package com.example.spring_boot_tutorial.Aexposition.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoachDto {
    String id;
    String name;
    // return also the details of fitness class of coach
    public List<FitnessClassCoachDetailsDto> fitnessClasses;
}
