package com.example.spring_boot_tutorial.Ddomain.coach;

import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClass;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "coach")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//classic approach using generated getters and setters
public class Coach {
    @Id
    @Column(name = "coach_id", nullable = false, unique = true)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "coach", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FitnessClass> fitnessClasses = new ArrayList<>();

}
