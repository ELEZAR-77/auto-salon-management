package com.example.autosalone.models.user.controller;


import com.example.autosalone.models.user.converters.UserDtoConverter;
import com.example.autosalone.models.user.dto.UserDto;
import com.example.autosalone.models.user.dto.UserRegisterRequestDto;
import com.example.autosalone.models.user.dto.UserUpdateRequestDto;
import com.example.autosalone.models.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserDtoConverter userDtoConverter;

    @PostMapping
    public ResponseEntity<UserDto> createUser(
             @Valid @RequestBody UserRegisterRequestDto userRequest
    ) {
        log.info("Get request for create User: {}", userRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userDtoConverter.toDto(
                        userService.createUser(
                                userDtoConverter.toDomainRegisterRequest(userRequest)
                        )
                ));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        log.info("Get request for getAllUsers");

        return ResponseEntity
                .ok(userService.getAllUsers()
                        .stream()
                        .map(userDtoConverter::toDto)
                        .toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserUpdateRequestDto updateRequest
    ) {
        log.info("Get request for update User: {}", updateRequest);

        return ResponseEntity
                .ok(userDtoConverter.toDto(
                        userService.updateUser(
                                id,
                                userDtoConverter.toDomainUpdateRequest(
                                        updateRequest))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(
            @PathVariable Long id
    ) {
        log.info("Get request for delete User: {}", id);

        userService.deleteUserById(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}
