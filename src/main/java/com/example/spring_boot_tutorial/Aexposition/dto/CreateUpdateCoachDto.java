package com.example.spring_boot_tutorial.Aexposition.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUpdateCoachDto {
    String id;
    String name;
    List<FitnessClassCoachDetailsDto> fitnessClasses;
}
