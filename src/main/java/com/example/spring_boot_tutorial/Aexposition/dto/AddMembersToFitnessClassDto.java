package com.example.spring_boot_tutorial.Aexposition.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class AddMembersToFitnessClassDto {
    String fitnessClassId;
    List<String> memberIds;
}
