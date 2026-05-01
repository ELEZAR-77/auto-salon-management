package com.example.autosalone.models.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegisterRequestDto(

        @Size(min = 2)
        @NotBlank
        String username,

        @Size(min = 8)
        @NotBlank
        String phoneNumber,

        @Email @NotBlank
        String email,

        @Size(min = 6) @NotBlank
        String password
) {
}
