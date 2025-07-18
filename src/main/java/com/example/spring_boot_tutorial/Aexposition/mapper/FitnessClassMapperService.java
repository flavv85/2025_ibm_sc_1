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
        Set<Member> memberSet = new HashSet<>();
        if (dto.getMemberIds() != null) {
            Set<String> memberIds = new HashSet<>(dto.getMemberIds());
            memberSet = getMembers(memberIds);
        }

        return FitnessClass
                .builder()
                .id(String.valueOf(UUID.randomUUID()))
                .name(dto.getName())
                .startTime(LocalDateTime.parse(dto.getStartTime()))
                .endTime(LocalDateTime.parse(dto.getEndTime()))
                .members(memberSet)
                // todo throw error if the coach does not exist
                .coach(coaches.getCoachById(dto.getCoachId()).orElseThrow(UnknownError::new))
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