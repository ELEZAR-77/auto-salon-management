package com.example.autosalone.models.user.service;

import com.example.autosalone.models.user.User;
import com.example.autosalone.models.user.UserEntity;
import com.example.autosalone.models.user.repository.UserRepository;
import com.example.autosalone.models.user.converters.UserEntityConverter;
import com.example.autosalone.models.user.exceptions.UserAlreadyExistException;
import com.example.autosalone.models.user.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserEntityConverter userEntityConverter;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public User createUser(User userRequest) {

        if (userRepository.existsByUsername(userRequest.username())) {
            throw new UserAlreadyExistException("Username already exists");
        }

        var hashedPassword = passwordEncoder.encode(userRequest.password());

        UserEntity userToCreate = userEntityConverter.toEntity(userRequest);
        userToCreate.setPassword(hashedPassword);

        return userEntityConverter.toDomain(
                userRepository.save(userToCreate)
        );
    }

    public List<User> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userEntityConverter::toDomain)
                .toList();
    }

    @Transactional
    public User updateUser(
            Long id,
            User userRequest
    ) {

        UserEntity userToUpdate = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User with id %s not found".formatted(id))
        );

        if (userRequest.username() != null) {
            userToUpdate.setUsername(userRequest.username());
        }

        if (userRequest.phoneNumber() != null) {
            userToUpdate.setPhoneNumber(userRequest.phoneNumber());
        }

        if (userRequest.email() != null) {
            userToUpdate.setEmail(userRequest.email());
        }

        if (userRequest.password() != null) {
            userToUpdate.setPassword(userRequest.password());
        }

        return userEntityConverter.toDomain(userToUpdate);
    }

    @Transactional
    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User with id %s not found".formatted(id));
        }

        userRepository.deleteById(id);
    }

    public User findByLogin(String login) {

        return userEntityConverter.toDomain(
                userRepository.findByUsername(login).orElseThrow(
                        () -> new UserNotFoundException("User with id %s not found".formatted(id))
                )
        );
    }
}
