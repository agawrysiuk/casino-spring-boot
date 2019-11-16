package com.agawrysiuk.casino.controller;

import com.agawrysiuk.casino.model.database.CasinoUser;
import com.agawrysiuk.casino.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path="/admin")
    public @ResponseBody CasinoUser getAdmin() {
        return userService.findCasinoUserByUsername("admin");
    }

    @GetMapping("/")
    public String index() {
        return "home";
    }

    // Login form
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    // Login form with error
    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    // Account page
    @GetMapping("/account")
    public String yourAccount(Model model, Principal principal) {
        CasinoUser casinoUser = userService.findCasinoUserByUsername(principal.getName());
        model.addAttribute("message", "You are logged in as " + principal.getName());

        return "account";
    }

}
