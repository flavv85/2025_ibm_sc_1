package com.example.spring_boot_tutorial.Ddomain.exceptions;

public class InvalidTimeRangeException extends RuntimeException {
    public InvalidTimeRangeException() { super("startTime must be before endTime"); }
}