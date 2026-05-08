package com.example.autosalone.models.user.dto;

import jakarta.validation.constraints.NotBlank;

public record SingInRequestDto(
        @NotBlank
        String login,

        @NotBlank
        String password
) {
}
