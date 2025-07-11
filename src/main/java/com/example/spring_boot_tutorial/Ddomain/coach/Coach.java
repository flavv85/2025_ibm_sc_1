package com.example.spring_boot_tutorial.Ddomain.coach;

import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClass;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "coach")
//classic approach using generated getters and setters
public class Coach {
    @Id
    @Column(name = "coach_id", nullable = false, unique = true)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "coach", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FitnessClass> fitnessClasses = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FitnessClass> getFitnessClasses() {
        return fitnessClasses;
    }

    public void setFitnessClasses(List<FitnessClass> fitnessClasses) {
        this.fitnessClasses = fitnessClasses;
    }

    // constructors
    public Coach() {
    }

    public Coach(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // in order to realise a correct and complete specific bidirectional relation
    // we need methods for bidirectional @OneToMany and @ManyToOne relations
    // for adding and deleting children (fitness classes) from parent entity (coach)

}
