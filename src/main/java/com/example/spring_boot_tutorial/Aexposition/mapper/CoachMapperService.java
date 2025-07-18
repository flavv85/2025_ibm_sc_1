package com.example.spring_boot_tutorial.Aexposition.mapper;

import com.example.spring_boot_tutorial.Aexposition.dto.CoachDto;
import com.example.spring_boot_tutorial.Aexposition.dto.CreateUpdateCoachDto;
import com.example.spring_boot_tutorial.Aexposition.dto.FitnessClassCoachDetailsDto;
import com.example.spring_boot_tutorial.Ddomain.coach.Coach;
import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClass;
import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClasses;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CoachMapperService {
    FitnessClasses fitnessClasses;

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

    public Coach mapFromDtoToEntity(CreateUpdateCoachDto dto) {
        List<FitnessClass> fitnessClassList = new ArrayList<>();
        if(dto.getFitnessClasses() != null) {
            List<String> fitnessClassesIds = dto.getFitnessClasses().stream().map(FitnessClassCoachDetailsDto::getId).toList();
            fitnessClassList = getFitnessClasses(fitnessClassesIds);
        }

        return Coach.builder()
                .id(dto.getId())
                .name(dto.getName())
                .fitnessClasses(fitnessClassList)
                .build();
    }

    private List<FitnessClass> getFitnessClasses(List<String> fitnessClassIds) {
        List<FitnessClass> fitnessClassList = new ArrayList<>();
        for(String fitnessClassId : fitnessClassIds) {
            fitnessClasses.getById(fitnessClassId).ifPresent(fitnessClassList::add);
        }
        return fitnessClassList;
    }
}