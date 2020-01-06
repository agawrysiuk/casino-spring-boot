package com.agawrysiuk.casino.controller;

import com.agawrysiuk.casino.model.database.CasinoUser;
import com.agawrysiuk.casino.service.SlotsService;
import com.agawrysiuk.casino.service.UserService;
import com.agawrysiuk.casino.util.AttributeNames;
import com.agawrysiuk.casino.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
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

    public SlotsController(SlotsService slotsService, UserService userService) {
        this.slotsService = slotsService;
        this.userService = userService;
    }

    @GetMapping(ViewNames.SLOTS)
    public String slots(Model model, Principal principal) {
        if (!userService.isEnoughMoney(principal.getName(), 1)) {
            return "redirect:/"+ViewNames.NO_MONEY_PAGE;
        }
        CasinoUser user = userService.findCasinoUserByUsername(principal.getName());
        model.addAttribute(AttributeNames.SLOTS_MONEY_MESSAGE, "Your balance is " + String.format("%1$,.2f", user.getBalance()) + " $.");
        model.addAttribute(AttributeNames.SLOTS_MAIN_MESSAGE, slotsService.getMessage());
        model.addAttribute(AttributeNames.SLOT_RESULTS, slotsService.getResults());
        log.info("model = {}", model);
        return ViewNames.SLOTS;
    }

    @RequestMapping(value = ViewNames.SLOTS, params = "roll", method = RequestMethod.POST)
    public String newRoll(Model model, Principal principal) {
        if (!userService.isEnoughMoney(principal.getName(), 1)) {
            return "redirect:/"+ViewNames.NO_MONEY_PAGE;
        }
        slotsService.roll();
        double userBalance = userService.findCasinoUserByUsername(principal.getName()).getBalance();
        double moneyResult = 1 * slotsService.getMultiplier();
        userBalance = userBalance - 1 + moneyResult;
        userService.updateCasinoUserBalance(userBalance, principal.getName());
        model.addAttribute(AttributeNames.SLOTS_MONEY_MESSAGE,
                "You bet 1 $. You got " + String.format("%1$,.2f", moneyResult) + " $. Your balance is now " + String.format("%1$,.2f", userBalance) + " $.");
        model.addAttribute(AttributeNames.SLOTS_MAIN_MESSAGE, slotsService.getMessage());
        model.addAttribute(AttributeNames.SLOT_RESULTS, slotsService.getResults());
        log.info("model = {}", model);
        return ViewNames.SLOTS;
    }

}
