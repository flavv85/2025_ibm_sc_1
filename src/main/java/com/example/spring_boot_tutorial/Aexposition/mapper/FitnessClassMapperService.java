package com.example.spring_boot_tutorial.Aexposition.mapper;

import com.example.spring_boot_tutorial.Aexposition.dto.ConsultFitnessClassDTO;
import com.example.spring_boot_tutorial.Aexposition.dto.CreateUpdateFitnessClassDto;
import com.example.spring_boot_tutorial.Ddomain.coach.Coaches;
import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClass;
import com.example.spring_boot_tutorial.Ddomain.member.Member;
import com.example.spring_boot_tutorial.Ddomain.member.Members;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Service
public class FitnessClassMapperService {

    Members members;
    Coaches coaches;

    public ConsultFitnessClassDTO mapToDto(FitnessClass fitnessClass) {
        Set<String> members = new HashSet<>();
        LocalDateTime startTime = fitnessClass.getStartTime();
        LocalDateTime endTime = fitnessClass.getEndTime();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String startTimeFormat = startTime.format(format);
        String endTimeFormat = endTime.format(format);
        for (var member : fitnessClass.getMembers()) {
            members.add(member.getNickname());
        }
        return ConsultFitnessClassDTO.builder()
                .coachName(fitnessClass.getCoach().getName())
                .members(members)
                .name(fitnessClass.getName())
                .id(fitnessClass.getId())
                .startTime(startTimeFormat)
                .endTime(endTimeFormat)
                .build();
    }

    // todo adapt method to also be used for updating (hint need and id, if null -> new, else will update)
    public FitnessClass mapToEntity(CreateUpdateFitnessClassDto dto) {
        return mapToEntity(dto, null);
    }


    public FitnessClass mapToEntity(CreateUpdateFitnessClassDto dto,String existingId) {

        String id = (existingId != null && !existingId.isBlank())
                ? existingId
                : UUID.randomUUID().toString();

        Set<Member> memberSet = null;
        if (dto.getMemberIds() != null) {
            memberSet = getMembers(new HashSet<>(dto.getMemberIds()));
        }

        return FitnessClass
                .builder()
                .id(id)
                .name(dto.getName())
                .startTime(dto.getStartTime()==null?null
                        :LocalDateTime.parse((dto.getStartTime())))
                .endTime(dto.getEndTime()==null?null
                        :LocalDateTime.parse(dto.getEndTime()))
                .members(memberSet)
                .coach(
                dto.getCoachId() == null ? null
                        : coaches.getCoachById(dto.getCoachId())
                        .orElseThrow(() ->
                                new IllegalArgumentException("Coach not found: " + dto.getCoachId()))
        )
                .build();
    }

    private Set<Member> getMembers(Set<String> memberDtoSet) {
        Set<Member> memberSet = new HashSet<>();
        for (String memberId : memberDtoSet) {
            members.getMemberById(memberId).ifPresent(memberSet::add);
        }
        return memberSet;
    }


}