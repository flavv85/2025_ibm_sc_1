package com.example.spring_boot_tutorial.Ddomain.exceptions;

public class UnknownObjectException extends RuntimeException {
    public UnknownObjectException(String message) {
        super(message);
    }
}
