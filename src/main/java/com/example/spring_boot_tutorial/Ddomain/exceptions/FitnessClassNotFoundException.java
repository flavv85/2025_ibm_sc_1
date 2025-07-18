package com.example.spring_boot_tutorial.Ddomain.exceptions;

public class FitnessClassNotFoundException extends RuntimeException {
    public FitnessClassNotFoundException(String id) { super("Class " + id + " not found"); }
}