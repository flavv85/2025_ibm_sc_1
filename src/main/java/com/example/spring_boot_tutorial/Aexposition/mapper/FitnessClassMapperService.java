package com.example.spring_boot_tutorial.Aexposition.mapper;

import com.example.spring_boot_tutorial.Aexposition.dto.FitnessClassDTO;
import com.example.spring_boot_tutorial.Aexposition.dto.MemberDto;
import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClass;
import com.example.spring_boot_tutorial.Ddomain.member.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

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
    public FitnessClassDTO mapFitnessClassFromEntityToDTO(FitnessClass fitnessClass) {
        return new FitnessClassDTO(fitnessClass.getId(), fitnessClass.getName(),fitnessClass.getMembers()
                .stream()
                .map(Member::getNickname)
                .toList());
    }

}
