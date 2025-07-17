package com.example.spring_boot_tutorial.Ddomain.Review;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "review")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Review {
    @Id
    @Column(name="review_id")
    String id;

    @Column(name = "coach_id",nullable = false)
    String coachId;

    @Column(name = "member_id",nullable = false)
    String memberId;

    @Column(name="mark",nullable = false)
    Float mark;
}
