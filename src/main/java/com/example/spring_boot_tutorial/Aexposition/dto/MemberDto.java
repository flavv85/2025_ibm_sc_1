package com.example.spring_boot_tutorial.Aexposition.dto;

import com.example.spring_boot_tutorial.Ddomain.member.MemberStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)

public class MemberDto {
    String id;
    String nickname;
    MemberStatus status;
}
