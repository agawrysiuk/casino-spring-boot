package com.agawrysiuk.casino.controller;

import com.agawrysiuk.casino.model.database.CasinoUser;
import com.agawrysiuk.casino.service.SlotsService;
import com.agawrysiuk.casino.service.UserService;
import com.agawrysiuk.casino.util.AttributeNames;
import com.agawrysiuk.casino.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Slf4j
@Controller
public class SlotsController {

    private final SlotsService slotsService;
    private final UserService userService;

    @Autowired
    public SlotsController(SlotsService slotsService, UserService userService) {
        this.slotsService = slotsService;
        this.userService = userService;
    }

    @GetMapping(ViewNames.SLOTS)
    public String slots(Model model,Principal principal) {
        CasinoUser user = userService.findUsername(principal.getName());
        model.addAttribute("moneyMessage", "Your balance is " + user.getBalance());
        model.addAttribute(AttributeNames.SLOTS_MAIN_MESSAGE, slotsService.getMessage());
        model.addAttribute("slotResults",slotsService.getResults());
        log.info("model = {}",model);
        return ViewNames.SLOTS;
    }

    @RequestMapping(value="/slots", params = "roll", method = RequestMethod.POST)
    public String newRoll(Model model, Principal principal) {
        slotsService.roll();
        CasinoUser user = userService.findUsername(principal.getName());
        double moneyResult = 1*slotsService.getMultiplier();
        user.setBalance(user.getBalance() - 1 + moneyResult);
        userService.updateBalance(user.getBalance(),user.getNickname());
        model.addAttribute("moneyMessage",
                "You bet 1 $. You got " + moneyResult + ". Your balance is now " + user.getBalance());
        model.addAttribute(AttributeNames.SLOTS_MAIN_MESSAGE, slotsService.getMessage());
        model.addAttribute("slotResults",slotsService.getResults());
        log.info("model = {}",model);
        return ViewNames.SLOTS;
    }

}
