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
public class UpdateMember {
    Members members;
    public void update(Member member){
        CheckIfMemberExist(member);
        members.CreateOrUpdate(member);


    }

    public void CheckIfMemberExist(Member member){
        members.getMemberById(member.getId())
                .orElseThrow(()->new UnknownMemberException(String.format("Member with id %s was not found",member.getId())));
    }
}
