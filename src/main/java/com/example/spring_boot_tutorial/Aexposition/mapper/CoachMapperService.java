package com.example.spring_boot_tutorial.Aexposition.mapper;

import com.example.spring_boot_tutorial.Aexposition.dto.CoachDto;
import com.example.spring_boot_tutorial.Aexposition.dto.FitnessClassCoachDetailsDto;
import com.example.spring_boot_tutorial.Ddomain.coach.Coach;
import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClass;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CoachMapperService {
    public CoachDto mapCoachFromEntityToDto(Coach coach) {
        return CoachDto
                .builder()
                .id(coach.getId())
                .name(coach.getName())
                // note: we are using key word this because we call a private method in this class (same class)
                .fitnessClasses(coach.getFitnessClasses().stream().map(this::mapFitnessClassFromDomain).toList())
                .build();
    }

    private FitnessClassCoachDetailsDto mapFitnessClassFromDomain(FitnessClass fitnessClass) {
        FitnessClassCoachDetailsDto fitnessClassCoachDetailsDto = new FitnessClassCoachDetailsDto();
        fitnessClassCoachDetailsDto.setId(fitnessClass.getId());
        fitnessClassCoachDetailsDto.setName(fitnessClass.getName());
        fitnessClassCoachDetailsDto.setDuration(fitnessClassCoachDetailsDto.appendHourToDuration(String.valueOf(fitnessClass.duration())));
        return fitnessClassCoachDetailsDto;
    }
}