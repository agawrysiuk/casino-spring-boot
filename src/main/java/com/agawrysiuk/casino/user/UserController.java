package com.agawrysiuk.casino.user;

import com.agawrysiuk.casino.casinouser.exception.IncorrectRequestException;
import com.agawrysiuk.casino.model.database.validator.PasswordDto;
import com.agawrysiuk.casino.user.request.LoginRequest;
import com.agawrysiuk.casino.util.ViewNames;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDto userDto) {
        return userFacade.register(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return userFacade.login(loginRequest);
    }

    @PostMapping("/edit-password")
    public ResponseEntity<?> editPassword(@RequestBody @Valid PasswordDto passwordDto, HttpServletRequest request) {
        if(!request.getUserPrincipal().getName().equals(passwordDto.getUsername())) {
            throw new IncorrectRequestException("Incorrect POST request!");
        }
        return userFacade.editPassword(passwordDto, request.getUserPrincipal());
    }

}
