package com.agawrysiuk.casino.user;

import com.agawrysiuk.casino.user.dto.CreditCardObjectDto;
import com.agawrysiuk.casino.user.dto.EditPasswordRequest;
import com.agawrysiuk.casino.user.dto.UserDto;
import com.agawrysiuk.casino.user.request.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @PutMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDto userDto) {
        return userFacade.register(userDto);
    }

    @PutMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return userFacade.login(loginRequest);
    }

    @PutMapping("/edit-password")
    public ResponseEntity<?> editPassword(@RequestBody @Valid EditPasswordRequest editPasswordRequest, HttpServletRequest request) {
        return userFacade.editPassword(editPasswordRequest, request.getUserPrincipal());
    }

    @PutMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody @Valid CreditCardObjectDto creditCardObjectDto, HttpServletRequest request) {
        return userFacade.deposit(request.getUserPrincipal(), creditCardObjectDto);
    }

}
