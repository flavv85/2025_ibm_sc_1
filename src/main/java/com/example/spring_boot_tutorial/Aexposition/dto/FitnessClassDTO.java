package com.example.spring_boot_tutorial.Aexposition.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FitnessClassDTO {
     String id;
     String name;

     List<String> memberNickNames;

}
