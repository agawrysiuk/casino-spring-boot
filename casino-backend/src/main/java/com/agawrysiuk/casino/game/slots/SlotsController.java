package com.agawrysiuk.casino.game.slots;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
@RestController
@RequestMapping("/v1/game")
@RequiredArgsConstructor
public class SlotsController {

    private final SlotsFacade slotsFacade;

    @GetMapping("/slots")
    public SlotsDto getInitial(HttpServletRequest request) {
        return slotsFacade.slots(request.getUserPrincipal().getName());
    }

    @PostMapping("/slots")
    public SlotsDto roll(HttpServletRequest request) {
        return slotsFacade.roll(request.getUserPrincipal().getName());
    }

}
