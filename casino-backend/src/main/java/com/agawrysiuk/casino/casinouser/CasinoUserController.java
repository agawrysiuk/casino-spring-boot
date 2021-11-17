package com.agawrysiuk.casino.casinouser;

import com.agawrysiuk.casino.casinouser.dto.CasinoUserDto;
import com.agawrysiuk.casino.casinouser.dto.EditCasinoUserRequest;
import com.agawrysiuk.casino.casinouser.exception.IncorrectRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class CasinoUserController {

    private final CasinoUserService casinoUserService;

    @GetMapping("/casino-user")
    public CasinoUserDto get(HttpServletRequest request) {
        return casinoUserService.get(request.getUserPrincipal().getName());
    }

    @PostMapping("/casino-user")
    public CasinoUserDto update(@RequestBody EditCasinoUserRequest casinoUserDto, HttpServletRequest request) {
        return casinoUserService.update(request.getUserPrincipal().getName(), casinoUserDto);
    }

}
