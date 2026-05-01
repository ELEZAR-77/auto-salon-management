package com.example.autosalone.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.FieldError;

import java.util.List;

@Data
@AllArgsConstructor
public class ValidationErrorResponse {

    private String message;
    private List<FieldError> fieldErrors;

    @Data
    @AllArgsConstructor
    public static class FieldError {
        private String field;
        private String message;
        private Object rejectedValue;
    }

    public ValidationErrorResponse(String message) {
        this.message = message;
    }
}
