package com.example.spring_boot_tutorial.Aexposition.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class ConsultMembersDTO {
    String id;
    String name;
    String nickname;
    String status;
}