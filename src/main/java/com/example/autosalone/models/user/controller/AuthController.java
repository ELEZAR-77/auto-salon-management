package com.example.autosalone.models.user.controller;

import com.example.autosalone.models.user.dto.AuthResponse;
import com.example.autosalone.models.user.dto.SingInRequestDto;
import com.example.autosalone.security.jwt.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody SingInRequestDto singInRequestDto
    ) {
        log.info("Got User authenticate request!");

        var token = authenticationService.authenticateUser(singInRequestDto);

        return ResponseEntity
                .ok()
                .body(new AuthResponse(token));
    }
}
