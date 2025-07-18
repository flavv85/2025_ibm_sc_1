package com.example.spring_boot_tutorial.Aexposition.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@AllArgsConstructor
@Builder
@Getter
public class ConsultFitnessClassDTO {
    private String id;
    private String name;
    private String startTime;
    private String endTime;
    private String coachName;
    private Set<String> members;

}