package com.example.autosalone.models.deal.exceptions;

public class CanceledDealException extends RuntimeException {
    public CanceledDealException(String message) {
        super(message);
    }
}
