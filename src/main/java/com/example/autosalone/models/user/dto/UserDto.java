package com.example.autosalone.models.user.dto;

public record UserDto(
        Long id,
        String username,
        String phoneNumber,
        String email
) {
}
