package com.example.spring_boot_tutorial.Aexposition.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FitnessClassDTO {
     String id;
     String name;
     String startTime;
     String endTime;
     String coachId;
     List<String> memberNickNames;
     Boolean isReady;

}
