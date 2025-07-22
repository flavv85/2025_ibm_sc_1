package com.example.spring_boot_tutorial.Bapplication.members;

import com.example.spring_boot_tutorial.Ddomain.exceptions.UnknownMemberException;
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
    public void Delete(String memberId){
        Member member=members.getMemberById(memberId)
                .orElseThrow(()->new UnknownMemberException(String.format("Members with id %s was not found",memberId)));
        members.DeleteMember(member);
    }
}
