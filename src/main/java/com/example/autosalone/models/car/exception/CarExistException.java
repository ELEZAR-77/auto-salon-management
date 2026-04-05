package com.example.autosalone.models.car.exception;

public class CarExistException extends RuntimeException {
    public CarExistException(String message) {
        super(message);
    }
}
