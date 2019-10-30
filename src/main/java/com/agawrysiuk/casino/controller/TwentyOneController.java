package com.agawrysiuk.casino.controller;

import com.agawrysiuk.casino.service.TwentyOneService;
import com.agawrysiuk.casino.util.AttributeNames;
import com.agawrysiuk.casino.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
public class TwentyOneController {

    private final TwentyOneService twentyOneService;

    @Autowired
    public TwentyOneController(TwentyOneService twentyOneService) {
        this.twentyOneService = twentyOneService;
    }

    @GetMapping(ViewNames.TWENTYONE)
    public String twentyone(Model model){
        twentyOneService.resetGame();
        model.addAttribute(AttributeNames.TWENTYONE_MAIN_MESSAGE, twentyOneService.getMainMessage());
        model.addAttribute(AttributeNames.TWENTYONE_RESULT_MESSAGE, twentyOneService.getResultMessage());
        model.addAttribute(AttributeNames.TWENTYONE_YOUR_HAND,twentyOneService.getYourCards());
        model.addAttribute(AttributeNames.TWENTYONE_DEALERS_HAND, twentyOneService.getDealersCards());
        model.addAttribute(AttributeNames.TWENTYONE_FINISHED,twentyOneService.getGameState());
        log.info("model = {}",model);
        return ViewNames.TWENTYONE;
    }

    @RequestMapping(value="/twentyone", params = "hitMe", method = RequestMethod.POST)
    public String newCard(Model model) {
        twentyOneService.hitMe();
        model.addAttribute(AttributeNames.TWENTYONE_MAIN_MESSAGE, twentyOneService.getMainMessage());
        model.addAttribute(AttributeNames.TWENTYONE_RESULT_MESSAGE, twentyOneService.getResultMessage());
        model.addAttribute(AttributeNames.TWENTYONE_YOUR_HAND,twentyOneService.getYourCards());
        model.addAttribute(AttributeNames.TWENTYONE_DEALERS_HAND, twentyOneService.getDealersCards());
        model.addAttribute(AttributeNames.TWENTYONE_FINISHED,twentyOneService.getGameState());
        log.info("model = {}",model);
        return ViewNames.TWENTYONE;
    }

    @RequestMapping(value="/twentyone", params = "hold", method = RequestMethod.POST)
    public String waitForDealer(Model model) {
        twentyOneService.dealersTurn();
        model.addAttribute(AttributeNames.TWENTYONE_MAIN_MESSAGE, twentyOneService.getMainMessage());
        model.addAttribute(AttributeNames.TWENTYONE_RESULT_MESSAGE, twentyOneService.getResultMessage());
        model.addAttribute(AttributeNames.TWENTYONE_YOUR_HAND,twentyOneService.getYourCards());
        model.addAttribute(AttributeNames.TWENTYONE_DEALERS_HAND, twentyOneService.getDealersCards());
        model.addAttribute(AttributeNames.TWENTYONE_FINISHED,twentyOneService.getGameState());
        log.info("model = {}",model);
        return ViewNames.TWENTYONE;
    }

    @RequestMapping(value="/twentyone", params = "again", method = RequestMethod.POST)
    public String playAgain(Model model) {
        twentyOneService.resetGame();
        model.addAttribute(AttributeNames.TWENTYONE_MAIN_MESSAGE, twentyOneService.getMainMessage());
        model.addAttribute(AttributeNames.TWENTYONE_RESULT_MESSAGE, twentyOneService.getResultMessage());
        model.addAttribute(AttributeNames.TWENTYONE_YOUR_HAND,twentyOneService.getYourCards());
        model.addAttribute(AttributeNames.TWENTYONE_DEALERS_HAND, twentyOneService.getDealersCards());
        model.addAttribute(AttributeNames.TWENTYONE_FINISHED,twentyOneService.getGameState());
        log.info("model = {}",model);
        return ViewNames.TWENTYONE;
    }
}
