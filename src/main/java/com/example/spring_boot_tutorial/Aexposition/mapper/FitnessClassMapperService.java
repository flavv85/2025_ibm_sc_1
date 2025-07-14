package com.example.spring_boot_tutorial.Aexposition.mapper;

import com.example.spring_boot_tutorial.Aexposition.dto.FitnessClassDTO;

import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClass;
import com.example.spring_boot_tutorial.Ddomain.member.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FitnessClassMapperService {

        //BUILDER NOT WORKING??? returns null
//    public FitnessClassDTO mapFitnessClassFromEntityToDto(FitnessClass fitnessClass) {
//        FitnessClassDTO fitnessClassDTO = new FitnessClassDTO();
//        fitnessClassDTO.builder()
//                .id(fitnessClass.getId())
//                .name(fitnessClass.getName())
//                .build();
//        return fitnessClassDTO;
//    }
//
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

}
