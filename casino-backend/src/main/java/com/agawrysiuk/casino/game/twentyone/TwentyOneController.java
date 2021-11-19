package com.agawrysiuk.casino.game.twentyone;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*")
@Slf4j
@RestController
@RequestMapping("/api/v1/game")
@RequiredArgsConstructor
public class TwentyOneController {

    private final TwentyOneFacade twentyOneFacade;

    @GetMapping("/twenty-one")
    public TwentyOneResponseDto twentyONe(HttpServletRequest request) {
        return twentyOneFacade.twentyOne(request.getUserPrincipal().getName());
    }

    @PostMapping("/twenty-one")
    public TwentyOneResponseDto nextMove(HttpServletRequest request, @RequestBody TwentyOneRequestDto requestDto) {
        return twentyOneFacade.nextMove(request.getUserPrincipal().getName(), requestDto);
    }
}
