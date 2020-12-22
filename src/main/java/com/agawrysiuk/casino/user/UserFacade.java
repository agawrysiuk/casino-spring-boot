package com.agawrysiuk.casino.user;

import com.agawrysiuk.casino.user.exception.EmailExistsException;
import com.agawrysiuk.casino.user.exception.UsernameExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserService userService;

    public ResponseEntity<?> register(UserDto userDto) {
        checkAvailability(userDto);
        userService.registerNewUserAccount(userDto, false);
        return ResponseEntity.ok("Registration successful!");
    }

    private void checkAvailability(UserDto userDto) {
        if (userService.existsByUsername(userDto.getUsername())) {
            throw new UsernameExistsException();
        }
        if (userService.existsByEmail(userDto.getEmail())) {
            throw new EmailExistsException();
        }
    }
}
