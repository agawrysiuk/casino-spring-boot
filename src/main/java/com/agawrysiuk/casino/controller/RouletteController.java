package com.agawrysiuk.casino.controller;

import com.agawrysiuk.casino.service.RouletteService;
import com.agawrysiuk.casino.service.UserService;
import com.agawrysiuk.casino.util.AttributeNames;
import com.agawrysiuk.casino.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Slf4j
@Controller
public class RouletteController {

    private final RouletteService rouletteService;
    private final UserService userService;

    public RouletteController(RouletteService rouletteService, UserService userService) {
        this.rouletteService = rouletteService;
        this.userService = userService;
    }

    @GetMapping(ViewNames.ROULETTE)
    public String roulette(Model model, Principal principal) {
        if (!userService.isEnoughMoney(principal.getName(), 1)) {
            return "redirect:/"+ViewNames.NO_MONEY_PAGE;
        }
        rouletteService.reset();
        double userBalance = userService.findCasinoUserByUsername(principal.getName()).getBalance();
        String message = "Your balance is " + String.format("%1$,.2f", userBalance) + " $.";
        model.addAttribute(AttributeNames.ROULETTE_MAIN_MESSAGE, rouletteService.getMainMessage());
        model.addAttribute(AttributeNames.ROULETTE_RESULT_MESSAGE, message);
        log.info("model = {}", model);
        return ViewNames.ROULETTE;
    }

    @RequestMapping(value = ViewNames.ROULETTE, params = "singleFormSubmit", method = RequestMethod.POST)
    public String rouletteSingle(HttpServletRequest request, Model model, @RequestParam int guessSingle, Principal principal) {
        if (!userService.isEnoughMoney(principal.getName(), 1)) {
            return "redirect:/"+ViewNames.NO_MONEY_PAGE;
        }
        rouletteService.roll();
        double userBalance = userService.findCasinoUserByUsername(principal.getName()).getBalance();
        StringBuilder resultMessage = new StringBuilder();
        resultMessage.append("You bet 1 $. ");
        String message = rouletteService.getResultMessageSingle(guessSingle);
        double moneyResult;
        if (message.equals("You lost")) {
            resultMessage.append(message);
            moneyResult = -1;
        } else {
            resultMessage.append("You won 35 $");
            moneyResult = 34;
        }
        return getRouletteString(model, principal, userBalance, resultMessage, moneyResult);
    }

    @RequestMapping(value = ViewNames.ROULETTE, params = "redOrBlackFormSubmit", method = RequestMethod.POST)
    public String rouletteRedOrBlackBet(HttpServletRequest request, Model model, @RequestParam String guessRedOrBlack, Principal principal) {
        if (!userService.isEnoughMoney(principal.getName(), 1)) {
            return "redirect:/"+ViewNames.NO_MONEY_PAGE;
        }
        rouletteService.roll();
        double userBalance = userService.findCasinoUserByUsername(principal.getName()).getBalance();
        StringBuilder resultMessage = new StringBuilder();
        resultMessage.append("You bet 1 $. ");
        String message = rouletteService.getResultMessageRedOrBlack(guessRedOrBlack);
        double moneyResult;
        if (message.contains("You lost")) {
            resultMessage.append(message);
            moneyResult = -1;
        } else {
            resultMessage.append("You won 2 $");
            moneyResult = 1;
        }
        return getRouletteString(model, principal, userBalance, resultMessage, moneyResult);
    }

    @RequestMapping(value = ViewNames.ROULETTE, params = "evenOrOddFormSubmit", method = RequestMethod.POST)
    public String rouletteEvenOrOddBet(HttpServletRequest request, Model model, @RequestParam String guessEvenOrOdd, Principal principal) {
        if (!userService.isEnoughMoney(principal.getName(), 1)) {
            return "redirect:/"+ViewNames.NO_MONEY_PAGE;
        }
        rouletteService.roll();
        double userBalance = userService.findCasinoUserByUsername(principal.getName()).getBalance();
        StringBuilder resultMessage = new StringBuilder();
        resultMessage.append("You bet 1 $. ");
        String message = rouletteService.getResultMessageEvenOrOdd(guessEvenOrOdd);
        double moneyResult;
        if (message.equals("You lost")) {
            resultMessage.append(message);
            moneyResult = -1;
        } else {
            resultMessage.append("You won 1 $");
            moneyResult = 1;
        }
        return getRouletteString(model, principal, userBalance, resultMessage, moneyResult);
    }

    private String getRouletteString(Model model, Principal principal, double userBalance, StringBuilder resultMessage, double moneyResult) {
        userBalance += moneyResult;
        resultMessage.append(". ");
        resultMessage.append("Your balance is now ").append(String.format("%1$,.2f", userBalance)).append(" $.");
        model.addAttribute(AttributeNames.ROULETTE_MAIN_MESSAGE, rouletteService.getMainMessage());
        model.addAttribute(AttributeNames.ROULETTE_RESULT_MESSAGE, resultMessage.toString());
        log.info("model = {}", model);
        userService.updateCasinoUserBalance(userBalance, principal.getName());
        return ViewNames.ROULETTE;
    }
}
