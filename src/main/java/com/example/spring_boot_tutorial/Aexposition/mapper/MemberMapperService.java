package com.example.spring_boot_tutorial.Aexposition.mapper;

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

    public MemberDto mapMemberFromEntityToDto(Member member, boolean onlyNickname) {
        return onlyNickname
                ? MemberDto.builder().nickname(member.getNickname()).build()
                : MemberDto.builder().id(member.getId()).nickname(member.getNickname()).build();
    }
}
