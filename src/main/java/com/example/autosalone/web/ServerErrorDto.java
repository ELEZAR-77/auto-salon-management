package com.example.autosalone.web;

import java.time.LocalDateTime;

public record ServerErrorDto (
        String message,
        String detailedMessage,
        LocalDateTime timestamp
){
}
