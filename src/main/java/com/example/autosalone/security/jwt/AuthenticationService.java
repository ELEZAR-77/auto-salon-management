package com.example.autosalone.security.jwt;

import com.example.autosalone.models.user.User;
import com.example.autosalone.models.user.UserEntity;
import com.example.autosalone.models.user.converters.UserEntityConverter;
import com.example.autosalone.models.user.dto.SingInRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenManager jwtTokenManager;
    private final UserEntityConverter userEntityConverter;

    public String authenticateUser(SingInRequestDto singInRequestDto) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        singInRequestDto.login(),
                        singInRequestDto.password()
                )
        );

        return jwtTokenManager.generateToken(singInRequestDto.login());
    }


    public User getCurrentAuthenticatedUserOrThrow() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
                throw new IllegalStateException("Authentication is not present in Context Holder");
        }


        return userEntityConverter.toDomain(
                (UserEntity) authentication.getPrincipal()
        );
    }
}
