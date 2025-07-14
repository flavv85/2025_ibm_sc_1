package com.example.spring_boot_tutorial.Aexposition.mapper;

import com.example.spring_boot_tutorial.Aexposition.dto.CreateUpdateFitnessClassDto;
import com.example.spring_boot_tutorial.Aexposition.dto.FitnessClassDTO;
import com.example.spring_boot_tutorial.Aexposition.dto.MemberDto;
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


    // todo adapt method to also be used for updating (hint need and id, if null -> new, else will update)
    public FitnessClass mapFromDtoToEntity(CreateUpdateFitnessClassDto dto) {
        Set<Member> memberSet = new HashSet<>();
        if (dto.getMembers() != null) {
            Set<String> memberIds = dto.getMembers().stream().map(MemberDto::getId).collect(Collectors.toSet());
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