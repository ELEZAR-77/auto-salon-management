package com.example.autosalone.models.car.exception;

public class CarNotExistException extends RuntimeException {
    public CarNotExistException(String message) {
        super(message);
    }
}
