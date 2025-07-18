package com.example.spring_boot_tutorial.Ddomain.coach;

import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClass;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "coach")
public class Coach {
    @Id
    @Column(name = "coach_id", nullable = false, unique = true)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "coach", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FitnessClass> fitnessClasses = new ArrayList<>();

    // in order to realise a correct and complete specific bidirectional relation
    // we need methods for bidirectional @OneToMany and @ManyToOne relations
    // for adding and deleting children (fitness classes) from parent entity (coach)

//    public void addFitnessClass(FitnessClass fitnessClass) {
//        fitnessClasses.add(fitnessClass);
//        fitnessClass.setCoach(this);
//    }
//
//    public void removeFitnessClass(FitnessClass fitnessClass) {
//        fitnessClasses.remove(fitnessClass);
//        fitnessClass.setCoach(null);
//    }

}
