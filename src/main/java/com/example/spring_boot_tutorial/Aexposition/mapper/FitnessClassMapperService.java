package com.example.spring_boot_tutorial.Aexposition.mapper;

import com.example.spring_boot_tutorial.Aexposition.FitnessClassValidator;
import com.example.spring_boot_tutorial.Aexposition.dto.CreateUpdateFitnessClassDto;
import com.example.spring_boot_tutorial.Aexposition.dto.FitnessClassDTO;
import com.example.spring_boot_tutorial.Aexposition.dto.MemberDto;
import com.example.spring_boot_tutorial.Ddomain.Exception.BussinessException;
import com.example.spring_boot_tutorial.Ddomain.Exception.UnknownObjectException;
import com.example.spring_boot_tutorial.Ddomain.coach.Coach;
import com.example.spring_boot_tutorial.Ddomain.coach.Coaches;
import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClass;
import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClasses;
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
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Service
public class FitnessClassMapperService {

    Members members;
    Coaches coaches;
    FitnessClasses fitnessClasses;
    FitnessClassValidator fitnessClassValidator;

    public FitnessClassDTO mapFitnessClassFromEntityToDTO(FitnessClass fitnessClass,String format) {
        boolean isReady = fitnessClassValidator.isMemberCountValid(fitnessClass.getMembers());

        DateTimeFormatter formatter = switch (format.toUpperCase()) {
            case "FULL" -> DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm:ss");
            case "SHORT" -> DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm");
            case "COMPACT" -> DateTimeFormatter.ofPattern("yy-MM-dd:HH:mm");
            default -> DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm:ss"); // fallback
        };

        return FitnessClassDTO.builder()
                .id(fitnessClass.getId())
                .name(fitnessClass.getName())
                .startTime(fitnessClass.getStartTime().format(formatter))
                .endTime(fitnessClass.getEndTime().format(formatter))
                .memberNickNames(fitnessClass.getMembers().stream().map(Member::getNickname).toList())
                .isReady(isReady)
                .build();
    }

    public FitnessClass mapFromDtoToEntity(CreateUpdateFitnessClassDto dto) {
        Set<Member> newMembers = new HashSet<>();
        if (dto.getMembers() != null) {
            Set<String> memberIds = dto.getMembers().stream()
                    .map(MemberDto::getId)
                    .collect(Collectors.toSet());
            newMembers = getMembers(memberIds);
        }

        if (!fitnessClassValidator.isMemberCountValid(newMembers)) {
            throw new BussinessException(String.format("Class has %d members. It must be between 3 and 8.", newMembers.size()));
        }

        FitnessClass fitnessClass;
        boolean isUpdate = StringUtils.hasText(dto.getId());

        if (isUpdate) {
            fitnessClass = fitnessClasses.getFitnessClassById(dto.getId())
                    .orElseThrow(() -> new UnknownObjectException(String.format("Fitness class with id %s does not exist", dto.getId())));
        } else {
            fitnessClass = new FitnessClass();
            fitnessClass.setId(UUID.randomUUID().toString());
        }
        fitnessClass.setName(dto.getName());
        fitnessClass.setStartTime(LocalDateTime.parse(dto.getStartTime()));
        fitnessClass.setEndTime(LocalDateTime.parse(dto.getEndTime()));

        fitnessClass.getMembers().clear();
        fitnessClass.getMembers().addAll(newMembers);

        if (StringUtils.hasText(dto.getCoachId())) {
            Coach coach = coaches.getCoachById(dto.getCoachId())
                    .orElseThrow(() -> new UnknownObjectException(String.format("Coach with id %s does not exist", dto.getCoachId())));
            fitnessClass.setCoach(coach);
        }
        return fitnessClass;
    }



    private Set<Member> getMembers(Set<String> memberDtoSet) {
        Set<Member> memberSet = new HashSet<>();
        for (String memberId : memberDtoSet) {
            members.getMemberById(memberId).ifPresent(memberSet::add);
        }
        return memberSet;
    }


    public FitnessClassDTO mapMinimalDTO(CreateUpdateFitnessClassDto dto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        FitnessClass fitnessClass = fitnessClasses.getFitnessClassById(dto.getId())
                .orElseThrow(() -> new UnknownObjectException(String.format("Fitness class with id %s does not exist", dto.getId())));

            Set<String> existingMemberIds = fitnessClass.getMembers().stream()
                        .map(Member::getId)
                        .collect(Collectors.toSet());

            List<String>  newMemberNicknames = dto.getMembers().stream()
                        .filter(m -> !existingMemberIds.contains(m.getId()))
                        .map(MemberDto::getNickname)
                        .toList();



        return FitnessClassDTO.builder()
                .id(fitnessClass.getId())
                .name(fitnessClass.getName())
                .startTime(fitnessClass.getStartTime().format(formatter))
                .endTime(fitnessClass.getEndTime().format(formatter))
                .coachId(dto.getCoachId())
                .memberNickNames(newMemberNicknames)
                .isReady(fitnessClass.getIsReady())
                .build();
    }



}