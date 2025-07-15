package com.example.spring_boot_tutorial.Aexposition.mapper;

import com.example.spring_boot_tutorial.Aexposition.dto.CreateUpdateMemberDto;
import com.example.spring_boot_tutorial.Aexposition.dto.MemberDto;
import com.example.spring_boot_tutorial.Ddomain.member.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MemberMapperService {

    public MemberDto mapMemberFromEntityToDto(Member member, boolean onlyNickname) {
        return onlyNickname
                ? MemberDto.builder().nickname(member.getNickname()).build()
                : MemberDto.builder().id(member.getId()).nickname(member.getNickname()).build();
    }

    public Member mapDtoToEntityFromDto(CreateUpdateMemberDto dto) {
        String id = StringUtils.hasText(dto.getId()) ? dto.getId() : UUID.randomUUID().toString();
        return Member.builder()
                .id(id)
                .name(dto.getName())
                .nickname(dto.getNickname())
                .build();
    }
}
