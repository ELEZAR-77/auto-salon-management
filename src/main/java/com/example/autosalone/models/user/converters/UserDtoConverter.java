package com.example.autosalone.models.user.converters;

import com.example.autosalone.models.user.User;
import com.example.autosalone.models.user.UserEntity;
import com.example.autosalone.models.user.UserRole;
import com.example.autosalone.models.user.dto.UserDto;
import com.example.autosalone.models.user.dto.UserRegisterRequestDto;
import com.example.autosalone.models.user.dto.UserUpdateRequestDto;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {

    public UserDto toDto(User user) {
        return new UserDto(
                user.id(),
                user.username(),
                user.phoneNumber(),
                user.email()
        );
    }

    public User toDomainRegisterRequest(UserRegisterRequestDto rq) {
        return new User(
                null,
                rq.username(),
                rq.phoneNumber(),
                rq.email(),
                rq.password(),
                UserRole.USER
        );
    }

    public User toDomainUpdateRequest(UserUpdateRequestDto rq) {
        return new User(
                null,
                rq.username(),
                rq.phoneNumber(),
                rq.email(),
                rq.password(),
                null
        );
    }
}
