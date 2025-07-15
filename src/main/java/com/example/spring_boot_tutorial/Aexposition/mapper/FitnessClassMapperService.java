package com.example.spring_boot_tutorial.Aexposition.mapper;

import com.example.spring_boot_tutorial.Aexposition.dto.CreateUpdateFitnessClassDto;
import com.example.spring_boot_tutorial.Aexposition.dto.FitnessClassDTO;
import com.example.spring_boot_tutorial.Aexposition.dto.MemberDto;
import com.example.spring_boot_tutorial.Ddomain.coach.Coach;
import com.example.spring_boot_tutorial.Ddomain.coach.Coaches;
import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClass;
import com.example.spring_boot_tutorial.Ddomain.member.Member;
import com.example.spring_boot_tutorial.Ddomain.member.Members;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Service
public class FitnessClassMapperService {

    Members members;
    Coaches coaches;

    public FitnessClassDTO mapFitnessClassFromEntityToDto(FitnessClass fitnessClass) {
        FitnessClassDTO fitnessClassDTO = new FitnessClassDTO();
        fitnessClassDTO.builder()
                .id(fitnessClass.getId())
                .name(fitnessClass.getName())
                .build();
        return fitnessClassDTO;
    }

    public FitnessClassDTO mapFitnessClassFromEntityToDTO(FitnessClass fitnessClass,String format) {
        DateTimeFormatter formatter = switch (format.toUpperCase()) {
            case "FULL" -> DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm:ss");
            case "SHORT" -> DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm");
            case "COMPACT" -> DateTimeFormatter.ofPattern("yy-MM-dd:HH:mm");
            default -> DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm:ss"); // fallback
        };

        return new FitnessClassDTO(
                fitnessClass.getId(),
                fitnessClass.getName(),
                fitnessClass.getStartTime().format(formatter),
                fitnessClass.getEndTime().format(formatter),
                fitnessClass.getMembers().stream()
                        .map(Member::getNickname)
                        .toList()
        );
    }

    public FitnessClass mapFromDtoToEntity(CreateUpdateFitnessClassDto dto) {
        Set<Member> memberSet = new HashSet<>();
        if (dto.getMembers() != null) {
            Set<String> memberIds = dto.getMembers().stream().map(MemberDto::getId).collect(Collectors.toSet());
            memberSet = getMembers(memberIds);
        }


        if (memberSet.size() <= 2 || memberSet.size() >= 9) {
            throw new IllegalArgumentException("Class has " + memberSet.size() + " members. It must be between 3 and 8.");
        }

        //Id+Coach Outside of builder for better readability
        String id = StringUtils.hasText(dto.getId()) ? dto.getId() : UUID.randomUUID().toString();
        Coach coach = coaches.getCoachById(dto.getCoachId())
                .orElseThrow(() -> new IllegalArgumentException("Coach with id " + dto.getCoachId() + " does not exist"));


        return FitnessClass
                .builder()
                .id(id)
                .name(dto.getName())
                .startTime(LocalDateTime.parse(dto.getStartTime()))
                .endTime(LocalDateTime.parse(dto.getEndTime()))
                .members(memberSet)
                .coach(coach)
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