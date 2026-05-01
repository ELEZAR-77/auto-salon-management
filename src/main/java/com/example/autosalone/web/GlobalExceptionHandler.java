package com.example.autosalone.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler
    public ResponseEntity<ServerErrorDto> handleGenericException(Exception ex) {
        log.error("Internal server error", ex);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        new ServerErrorDto(
                                "Ошибка сервера",
                                ex.getMessage(),
                                LocalDateTime.now()
                        )
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationErrorResponseEntity(
            MethodArgumentNotValidException ex
    ) {
        List<ValidationErrorResponse.FieldError> fieldErrors = ex.getBindingResult()
                .getFieldErrors().stream()
                .map(fieldError -> new ValidationErrorResponse.FieldError(
                        fieldError.getField(),
                        fieldError.getDefaultMessage(),
                        fieldError.getRejectedValue()
                ))
                .toList();

        String message = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError ->
                        fieldError.getField() + ": " +
                        fieldError.getDefaultMessage() + ": " +
                        fieldError.getRejectedValue()
                ).collect(Collectors.joining());

        ValidationErrorResponse response = new ValidationErrorResponse(
                message,
                fieldErrors
        );

        log.error("Validation check error caught.", ex);

        return  ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);

    }

}
