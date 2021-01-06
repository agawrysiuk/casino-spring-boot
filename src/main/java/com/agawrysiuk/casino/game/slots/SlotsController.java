package com.agawrysiuk.casino.game.slots;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
@RestController
@RequiredArgsConstructor
public class SlotsController {

    private final SlotsFacade slotsFacade;

    @GetMapping("/slots")
    public SlotsDto slots(HttpServletRequest request) {
        return slotsFacade.slots(request.getUserPrincipal().getName());
    }

    @PostMapping("/slots")
    public SlotsDto newRoll(HttpServletRequest request) {
        return slotsFacade.newRoll(request.getUserPrincipal().getName());
    }

}
