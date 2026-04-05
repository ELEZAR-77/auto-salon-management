package com.example.autosalone.models.user;

public record User(
        Long id,
        String username,
        String password,
        UserRole role
) {
}
