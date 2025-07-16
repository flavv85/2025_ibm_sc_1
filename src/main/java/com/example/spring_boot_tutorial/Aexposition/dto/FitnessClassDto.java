package com.example.spring_boot_tutorial.Aexposition.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FitnessClassDto {
    String id;
    String name;
    String startTime;
    String endTime;
    String coachName;
    Set<String> members;
}
