package com.agawrysiuk.casino.controller;

import com.agawrysiuk.casino.service.SlotsService;
import com.agawrysiuk.casino.util.AttributeNames;
import com.agawrysiuk.casino.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class SlotsController {

    private final SlotsService slotsService;

    @Autowired
    public SlotsController(SlotsService slotsService) {
        this.slotsService = slotsService;
    }


    @GetMapping(ViewNames.SLOTS)
    public String slots(Model model) {
        model.addAttribute(AttributeNames.SLOTS_MAIN_MESSAGE, slotsService.getMessage());
        model.addAttribute("slotResults",slotsService.getResults());
        log.info("model = {}",model);
        return ViewNames.SLOTS;
    }

    @RequestMapping(value="/slots", method = RequestMethod.POST)
    public String newRoll(Model model) {
        slotsService.roll();
        model.addAttribute(AttributeNames.SLOTS_MAIN_MESSAGE, slotsService.getMessage());
        model.addAttribute("slotResults",slotsService.getResults());
        log.info("model = {}",model);
        return ViewNames.SLOTS;
    }

}
