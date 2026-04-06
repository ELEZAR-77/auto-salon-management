package com.example.autosalone.models.user.converters;

import com.example.autosalone.models.user.User;
import com.example.autosalone.models.user.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserEntityConverter {


    public UserEntity toEntity(User user) {
        return new UserEntity(
                user.id(),
                user.username(),
                user.phoneNumber(),
                user.email(),
                user.password(),
                user.role()
        );
    }

    public User toDomain(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getPhoneNumber(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getRole()
        );
    }
}
