package com.example.autosalone.models.car.exception;

public class CarModelNotFound extends RuntimeException {
    public CarModelNotFound(String message) {
        super(message);
    }
}
