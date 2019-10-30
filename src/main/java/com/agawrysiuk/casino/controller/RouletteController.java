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

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class RouletteController {

    private final RouletteService rouletteService;

    @Autowired
    public RouletteController(RouletteService rouletteService) {
        this.rouletteService = rouletteService;
    }

    @GetMapping("/") // change to ViewNames.ROULETTE later on
    public String roulette(Model model) {
        model.addAttribute(AttributeNames.ROULETTE_MAIN_MESSAGE,rouletteService.getMainMessage());
        log.info("model = {}",model);
        return ViewNames.ROULETTE;
    }

    @RequestMapping(params = "single", method = RequestMethod.POST)
    public String rouletteSingleBet(HttpServletRequest request, Model model) {
        rouletteService.roll();
        model.addAttribute(AttributeNames.ROULETTE_MAIN_MESSAGE,rouletteService.getMainMessage());
        log.info("model = {}",model);
        return ViewNames.ROULETTE;
    }
}
