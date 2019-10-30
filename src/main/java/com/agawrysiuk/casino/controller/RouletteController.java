package com.agawrysiuk.casino.controller;

import com.agawrysiuk.casino.service.RouletteService;
import com.agawrysiuk.casino.util.AttributeNames;
import com.agawrysiuk.casino.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class RouletteController {

    private final RouletteService rouletteService;

    @Autowired
    public RouletteController(RouletteService rouletteService) {
        this.rouletteService = rouletteService;
    }

    @GetMapping(ViewNames.ROULETTE)
    public String roulette(Model model) {
        rouletteService.reset();
        model.addAttribute(AttributeNames.ROULETTE_MAIN_MESSAGE,rouletteService.getMainMessage());
        log.info("model = {}",model);
        return ViewNames.ROULETTE;
    }

    @RequestMapping(value="/roulette", params = "singleFormSubmit", method = RequestMethod.POST)
    public String rouletteSingle(HttpServletRequest request, Model model, @RequestParam int guessSingle) {
        rouletteService.roll();
        model.addAttribute(AttributeNames.ROULETTE_MAIN_MESSAGE,rouletteService.getMainMessage());
        model.addAttribute(AttributeNames.ROULETTE_RESULT_MESSAGE,rouletteService.getResultMessageSingle(guessSingle));
        log.info("model = {}",model);
        return ViewNames.ROULETTE;
    }

    @RequestMapping(value="/roulette", params = "redOrBlackFormSubmit", method = RequestMethod.POST)
    public String rouletteRedOrBlackBet(HttpServletRequest request, Model model, @RequestParam String guessRedOrBlack) {
        rouletteService.roll();
        model.addAttribute(AttributeNames.ROULETTE_MAIN_MESSAGE,rouletteService.getMainMessage());
        model.addAttribute(AttributeNames.ROULETTE_RESULT_MESSAGE,rouletteService.getResultMessageRedOrBlack(guessRedOrBlack));
        log.info("model = {}",model);
        return ViewNames.ROULETTE;
    }

    @RequestMapping(value="/roulette", params = "evenOrOddFormSubmit", method = RequestMethod.POST)
    public String rouletteEvenOrOddBet(HttpServletRequest request, Model model, @RequestParam String guessEvenOrOdd) {
        rouletteService.roll();
        model.addAttribute(AttributeNames.ROULETTE_MAIN_MESSAGE,rouletteService.getMainMessage());
        model.addAttribute(AttributeNames.ROULETTE_RESULT_MESSAGE,rouletteService.getResultMessageEvenOrOdd(guessEvenOrOdd));
        log.info("model = {}",model);
        return ViewNames.ROULETTE;
    }
}
