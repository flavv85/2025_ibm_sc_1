package com.example.spring_boot_tutorial.Aexposition.mapper;

import com.example.spring_boot_tutorial.Aexposition.dto.FitnessClassDto;
import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClass;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FitnessClassMapperService {

    public FitnessClassDto mapFitnessClassFromEntityToDto(FitnessClass fitnessClass) {
        Set<String> members = new HashSet<>();
        LocalDateTime startTime = fitnessClass.getStartTime();
        LocalDateTime endTime = fitnessClass.getEndTime();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String startTimeFormat = startTime.format(format);
        String endTimeFormat = endTime.format(format);
        for (var member : fitnessClass.getMembers()) {
            members.add(member.getNickname());
        }
        return FitnessClassDto.builder()
                .coachName(fitnessClass.getCoach().getName())
                .members(members)
                .name(fitnessClass.getName())
                .id(fitnessClass.getId())
                .startTime(startTimeFormat)
                .endTime(endTimeFormat)
                .build();
    }
}
