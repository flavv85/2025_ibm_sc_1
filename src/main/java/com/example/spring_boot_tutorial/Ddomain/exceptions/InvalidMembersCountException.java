package com.example.spring_boot_tutorial.Ddomain.exceptions;

public class InvalidMembersCountException extends  RuntimeException{
    public InvalidMembersCountException(int size) {
        super("Fitness class must have between 3 and 8 members, but got " + size + ".");
    }
}
