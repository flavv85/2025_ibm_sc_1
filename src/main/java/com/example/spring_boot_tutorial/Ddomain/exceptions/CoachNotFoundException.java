package com.example.spring_boot_tutorial.Ddomain.exceptions;

public class CoachNotFoundException extends RuntimeException{
    public CoachNotFoundException(String coachId) {
        super("Coach with id '" + coachId + "' does not exist.");
    }
}
