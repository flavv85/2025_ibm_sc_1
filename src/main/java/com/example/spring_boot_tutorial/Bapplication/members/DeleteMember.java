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
public class DeleteMember {
    Members members;

    public void deleteMember(String memberId) {
        Member member = members.getMemberById(memberId).orElseThrow(UnknownError::new);
        members.deleteMember(member);
    }
}
