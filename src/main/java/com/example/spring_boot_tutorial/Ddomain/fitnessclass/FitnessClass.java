package com.example.spring_boot_tutorial.Ddomain.fitnessclass;

import com.example.spring_boot_tutorial.Ddomain.coach.Coach;
import com.example.spring_boot_tutorial.Ddomain.member.Member;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "fitness_class")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FitnessClass {

    @Id
    @Column(name = "ID")
    String id;

    @Column(name = "NAME", nullable = false)
    String name;

    @Column(name = "START_TIME")
    LocalDateTime startTime;

    @Column(name = "END_TIME")
    LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "COACH_ID", nullable = false)
    Coach coach;

    @ManyToMany
    @JoinTable(name = "FITNESS_CLASSES_MEMBERS",
            joinColumns = @JoinColumn(name = "fitness_class_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id"))
    Set<Member> members = new HashSet<>();

    // helper class method to be reused when ever duration between start and end time is needed.
    public Long duration() {
        return Duration.between(this.startTime, this.endTime).toHours();
    }

}

