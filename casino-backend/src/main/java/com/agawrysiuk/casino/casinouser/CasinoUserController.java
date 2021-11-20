package com.agawrysiuk.casino.casinouser;

import com.agawrysiuk.casino.casinouser.dto.CasinoUserDto;
import com.agawrysiuk.casino.casinouser.dto.EditCasinoUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/v1/casino-user")
@RestController
@RequiredArgsConstructor
public class CasinoUserController {

    private final CasinoUserService casinoUserService;

    @GetMapping()
    public ResponseEntity<CasinoUserDto> get(HttpServletRequest request) {
        return new ResponseEntity<>(casinoUserService.get(request.getUserPrincipal().getName()), HttpStatus.OK);
    }

    @PatchMapping()
    public ResponseEntity<CasinoUserDto> update(@RequestBody EditCasinoUserRequest casinoUserDto, HttpServletRequest request) {
        return new ResponseEntity<>(casinoUserService.update(request.getUserPrincipal().getName(), casinoUserDto), HttpStatus.ACCEPTED);
    }

}
