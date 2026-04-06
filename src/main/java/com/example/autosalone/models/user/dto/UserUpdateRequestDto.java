package com.example.autosalone.models.user.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserUpdateRequestDto(
        @Size(min = 2) @Nullable
        String username,

        @Size(min = 8) @Nullable
        String phoneNumber,

        @Email @Nullable
        String email,

        @Size(min = 6) @Nullable
        String password
) {
}
