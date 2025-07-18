package com.example.spring_boot_tutorial.Aexposition.mapper;

import com.example.spring_boot_tutorial.Aexposition.dto.CreateUpdateMemberDto;
import com.example.spring_boot_tutorial.Aexposition.dto.MemberDto;
import com.example.spring_boot_tutorial.Ddomain.member.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MemberMapperService {
    public MemberDto mapMemberFromEntityToDto(Member member) {
        return new MemberDto(member.getId(), member.getNickname());
    }

    public Member mapFromDtoToEntity(CreateUpdateMemberDto dto) {
        return new Member(dto.getId(), dto.getName(), dto.getNickname());
    }
}
