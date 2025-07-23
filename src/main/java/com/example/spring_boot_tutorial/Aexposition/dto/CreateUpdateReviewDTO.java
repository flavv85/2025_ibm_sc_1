package com.example.spring_boot_tutorial.Aexposition.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor

public class CreateUpdateReviewDTO {
    String coach_id;
    String member_id;
    int mark;
}
