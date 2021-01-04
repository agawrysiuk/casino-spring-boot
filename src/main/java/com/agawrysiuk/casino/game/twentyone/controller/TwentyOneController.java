package com.agawrysiuk.casino.game.twentyone.controller;

import com.agawrysiuk.casino.game.twentyone.TwentyOneService;
import com.agawrysiuk.casino.user.UserService;
import com.agawrysiuk.casino.util.AttributeNames;
import com.agawrysiuk.casino.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;
import java.security.Principal;

@Slf4j
@Controller
public class TwentyOneController {

    private final TwentyOneService twentyOneService;
    private final UserService userService;

    public TwentyOneController(TwentyOneService twentyOneService, UserService userService) {
        this.twentyOneService = twentyOneService;
        this.userService = userService;
    }

    @GetMapping(ViewNames.TWENTYONE)
    public String twentyone(Model model, Principal principal) {
        if (!userService.isEnoughMoney(principal.getName(), BigDecimal.valueOf(10))) {
            return "redirect:/"+ViewNames.NO_MONEY_PAGE;
        }
        twentyOneService.resetGame();
        BigDecimal userBalance = userService.findCasinoUserByUsername(principal.getName()).getBalance();
        String resultMessage = "Your balance is " + String.format("%1$,.2f", userBalance) + " $. Bet is 10 $.";
        model.addAttribute(AttributeNames.TWENTYONE_MAIN_MESSAGE, twentyOneService.getMainMessage());
        model.addAttribute(AttributeNames.TWENTYONE_RESULT_MESSAGE, resultMessage);
        model.addAttribute(AttributeNames.TWENTYONE_YOUR_HAND, twentyOneService.getYourCards());
        model.addAttribute(AttributeNames.TWENTYONE_DEALERS_HAND, twentyOneService.getDealersCards());
        model.addAttribute(AttributeNames.TWENTYONE_FINISHED, twentyOneService.getGameState());
        log.info("model = {}", model);
        return ViewNames.TWENTYONE;
    }

    @RequestMapping(value = ViewNames.TWENTYONE, params = "hitMe", method = RequestMethod.POST)
    public String newCard(Model model, Principal principal) {
        twentyOneService.hitMe();
        return getGameString(model, principal);
    }

    @RequestMapping(value = ViewNames.TWENTYONE, params = "hold", method = RequestMethod.POST)
    public String waitForDealer(Model model, Principal principal) {
        twentyOneService.dealersTurn();
        return getGameString(model, principal);
    }

    @RequestMapping(value = ViewNames.TWENTYONE, params = "again", method = RequestMethod.POST)
    public String playAgain(Model model, Principal principal) {
        return twentyone(model, principal);
    }

    private String getGameString(Model model, Principal principal) {
        boolean gameState = twentyOneService.getGameState();
        BigDecimal userBalance = userService.findCasinoUserByUsername(principal.getName()).getBalance();
        String moneyMessage = "";
        if (gameState) {
            if (twentyOneService.getGameResult()) {
                userBalance = userBalance.add(BigDecimal.valueOf(10));
                moneyMessage = "You won 10 $. ";
            } else {
                userBalance = userBalance.add(BigDecimal.valueOf(-10));
                moneyMessage = "You lost 10 $. ";
            }
            moneyMessage += "Your balance is " + String.format("%1$,.2f", userBalance) + " $.";
        }
        userService.updateCasinoUserBalance(userBalance, principal.getName());
        model.addAttribute(AttributeNames.TWENTYONE_MAIN_MESSAGE, twentyOneService.getMainMessage());
        model.addAttribute(AttributeNames.TWENTYONE_RESULT_MESSAGE, twentyOneService.getResultMessage());
        model.addAttribute(AttributeNames.TWENTYONE_YOUR_HAND, twentyOneService.getYourCards());
        model.addAttribute(AttributeNames.TWENTYONE_DEALERS_HAND, twentyOneService.getDealersCards());
        model.addAttribute(AttributeNames.TWENTYONE_FINISHED, gameState);
        model.addAttribute(AttributeNames.TWENTYONE_MONEY_MESSAGE, moneyMessage);
        log.info("model = {}", model);
        return ViewNames.TWENTYONE;
    }
}
