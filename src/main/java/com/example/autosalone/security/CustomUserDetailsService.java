package com.example.autosalone.security;

import com.example.autosalone.models.user.UserEntity;
import com.example.autosalone.models.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public @NonNull UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {

        UserEntity user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found with username: " + username)
        );

        return User.withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }
}
