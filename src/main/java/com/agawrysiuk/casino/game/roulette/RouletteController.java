package com.agawrysiuk.casino.game.roulette;

import com.agawrysiuk.casino.user.UserService;
import com.agawrysiuk.casino.util.AttributeNames;
import com.agawrysiuk.casino.util.ViewNames;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;

@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
@RequiredArgsConstructor
@RestController
public class RouletteController {

    private final RouletteFacade rouletteFacade;

    @GetMapping("/roulette")
    public RouletteResponseDto getInitial(HttpServletRequest request) {
        return rouletteFacade.roulette(request.getUserPrincipal().getName());
    }

    @PostMapping("/roulette")
    public RouletteResponseDto roll(HttpServletRequest request, @RequestBody @Valid RouletteRequestDto requestDto) {
        return rouletteFacade.roll(request.getUserPrincipal().getName(), requestDto);
    }
}
