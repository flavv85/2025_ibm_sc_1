package com.example.spring_boot_tutorial.Ddomain.member;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "member")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
//using annotations for getters, setters, constructors and builder pattern
public class Member {

    @Id
    @Column(name = "member_id")
    String id;

    @Column(name = "member_name")
    String name;

    @Column(name = "member_nickname")
    String nickname;
}
