package com.example.spring_boot_tutorial.Aexposition.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateUpdateFitnessClassDto {
    String id;
    String name;
    String startTime;
    String endTime;
    String coachId;
    Set<MemberDto> members;
    Boolean isReady;
}
