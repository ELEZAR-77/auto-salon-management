package com.example.autosalone.models.user;

public record User(
        Long id,
        String username,
        String phoneNumber,
        String email,
        String password,
        UserRole role
) {
}
