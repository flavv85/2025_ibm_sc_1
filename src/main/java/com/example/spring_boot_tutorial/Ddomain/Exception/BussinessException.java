package com.example.spring_boot_tutorial.Ddomain.Exception;

public class BussinessException extends RuntimeException {
    public BussinessException(String message) {
        super(message);
    }
}
