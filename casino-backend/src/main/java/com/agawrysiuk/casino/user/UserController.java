package com.agawrysiuk.casino.user;

import com.agawrysiuk.casino.user.dto.CreditCardObjectDto;
import com.agawrysiuk.casino.user.dto.EditPasswordRequest;
import com.agawrysiuk.casino.user.dto.UserDto;
import com.agawrysiuk.casino.user.request.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> editPassword(@RequestBody @Valid EditPasswordRequest editPasswordRequest, HttpServletRequest request) {
        return userFacade.editPassword(editPasswordRequest, request.getUserPrincipal());
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody @Valid CreditCardObjectDto creditCardObjectDto, HttpServletRequest request) {
        return userFacade.deposit(request.getUserPrincipal(), creditCardObjectDto);
    }

}
