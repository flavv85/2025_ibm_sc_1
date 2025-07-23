package com.example.spring_boot_tutorial.Aexposition.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CreateUpdateMemberDTO {
    public String name;
    public String nickname;
}