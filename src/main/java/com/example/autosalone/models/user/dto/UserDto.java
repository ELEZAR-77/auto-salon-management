package com.example.autosalone.models.user.dto;

import com.example.autosalone.models.user.UserRole;

public record UserDto(
        Long id,
        String username,
        String phoneNumber,
        String email,
        UserRole role
) {
}
