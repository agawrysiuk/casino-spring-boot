package com.agawrysiuk.casino.casinouser;

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
    public CasinoUserDto get(@RequestParam("name") String nickname, HttpServletRequest request) {
        if(!request.getUserPrincipal().getName().equals(nickname)) {
            throw new IncorrectRequestException("Incorrect GET request!");
        }
        return casinoUserService.get(nickname);
    }

    @PostMapping("/casino-user")
    public CasinoUserDto update(@RequestBody CasinoUserDto casinoUserDto, HttpServletRequest request) {
        if(!request.getUserPrincipal().getName().equals(casinoUserDto.getNickname())) {
            throw new IncorrectRequestException("Incorrect POST request!");
        }
        return casinoUserService.update(casinoUserDto);
    }

}
