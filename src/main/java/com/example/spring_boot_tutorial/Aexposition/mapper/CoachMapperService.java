package com.example.spring_boot_tutorial.Aexposition.mapper;

import com.example.spring_boot_tutorial.Aexposition.dto.ConsultCoachDto;
import com.example.spring_boot_tutorial.Aexposition.dto.FitnessClassCoachDetailsDto;
import com.example.spring_boot_tutorial.Ddomain.coach.Coach;
import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClass;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Service
public class CoachMapperService {

    public ConsultCoachDto mapFromDomain(Coach coach) {
        return ConsultCoachDto
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
    public Coach mapToEntity(ConsultCoachDto dto, String coachId) {
        String id = StringUtils.hasText(coachId) ? coachId : UUID.randomUUID().toString();
        return Coach.builder()
                .id(id)
                .name(dto.getName())
                .build();
    }
    public void updateEntity(Coach coach, ConsultCoachDto dto) {
        coach.setName(dto.getName());
    }



}