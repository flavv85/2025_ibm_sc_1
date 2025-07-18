package com.example.spring_boot_tutorial.Aexposition.mapper;

import com.example.spring_boot_tutorial.Aexposition.dto.ConsultCoachDto;
import com.example.spring_boot_tutorial.Aexposition.dto.CreateUpdateCoachDto;
import com.example.spring_boot_tutorial.Aexposition.dto.FitnessClassCoachDetailsDto;
import com.example.spring_boot_tutorial.Ddomain.coach.Coach;
import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClass;
import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClasses;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.flywaydb.core.internal.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Service
public class CoachMapperService {

    FitnessClassMapperService fitnessClassMapperService;
    FitnessClasses fitnessClasses;

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

    public Coach mapDtoToEntity(CreateUpdateCoachDto dto) {
        String id = StringUtils.hasText(dto.getId()) ? dto.getId() : UUID.randomUUID().toString();
        List<FitnessClass> fitnessClassList = new ArrayList<>();
        if(dto.getFitnessClasses() != null) {
            List<String> fitnessClassesIds = dto.getFitnessClasses().stream()
                    .map(FitnessClassCoachDetailsDto::getId)
                    .collect(Collectors.toList());
            fitnessClassList = getFitnessClasses(fitnessClassesIds);
        }
        return Coach.builder()
                .id(id)
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