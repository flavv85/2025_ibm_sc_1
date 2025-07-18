package com.example.spring_boot_tutorial.Bapplication.members;

import com.example.spring_boot_tutorial.Ddomain.member.Member;
import com.example.spring_boot_tutorial.Ddomain.member.Members;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UpdateMember{

    Members members;

    public void UpdateMember(Member member) {
        member.setId(String.valueOf(member.getId()));
        members.updateMember(member);
    }
}
