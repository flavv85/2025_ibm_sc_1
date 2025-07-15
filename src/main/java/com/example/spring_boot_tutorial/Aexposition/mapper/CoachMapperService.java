package com.example.spring_boot_tutorial.Aexposition.mapper;

import com.example.spring_boot_tutorial.Aexposition.dto.CoachDto;
import com.example.spring_boot_tutorial.Aexposition.dto.CreateUpdateCoachDto;
import com.example.spring_boot_tutorial.Aexposition.dto.FitnessClassDTO;
import com.example.spring_boot_tutorial.Ddomain.coach.Coach;
import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClass;
import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClasses;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CoachMapperService {

    FitnessClasses fitnessClasses;

    public CoachDto mapCoachFromEntityToDto(Coach coach) {
        return new CoachDto(coach.getName(), coach.getId());
    }

    public Coach mapDtoToEntity(CreateUpdateCoachDto dto) {
        String id = StringUtils.hasText(dto.getId()) ? dto.getId() : UUID.randomUUID().toString();
        List<FitnessClass> fitnessClassList = new ArrayList<>();
        if(dto.getFitnessClasses() != null) {
            List<String> fitnessClassesIds = dto.getFitnessClasses().stream()
                    .map(FitnessClassDTO::getId)
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
            fitnessClasses.getFitnessClassById(fitnessClassId).ifPresent(fitnessClassList::add);
        }
        return fitnessClassList;
    }
}
