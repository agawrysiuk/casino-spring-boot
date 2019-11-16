package com.agawrysiuk.casino.controller;

import com.agawrysiuk.casino.model.database.CasinoUser;
import com.agawrysiuk.casino.model.database.PasswordDto;
import com.agawrysiuk.casino.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;

@Slf4j
@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/admin")
    public @ResponseBody
    CasinoUser getAdmin() {
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
        log.info("casinoUser = {}", casinoUser);
        model.addAttribute("balance", String.format("%1$,.2f", casinoUser.getBalance()) + " $");
        model.addAttribute("casinoUser", casinoUser);
        model.addAttribute("message", "You are logged in as " + principal.getName());
        return "account";
    }

    @RequestMapping(value = "/password", method = RequestMethod.GET)
    public String showRegistrationForm(WebRequest request, Model model) {
        PasswordDto passwordDto = new PasswordDto();
        model.addAttribute("passwordDto", passwordDto);
        return "password";
    }

    @RequestMapping(value = "/password", params="updatePassword", method = RequestMethod.POST)
    public ModelAndView changePassword(
            @ModelAttribute("passwordDto") @Valid PasswordDto passwordDto,
            BindingResult result,
            WebRequest request,
            Errors errors,
            Principal principal) {

        log.info("changePassword() started");
        if (!result.hasErrors()) {
            log.info("changePassword() ---- NO ERRORS");
            if (!BCrypt.checkpw(passwordDto.getOldPassword(),userService.findUserByUsername(principal.getName()).getPassword())) {
                result.addError(new FieldError("passwordDto","oldPassword","Incorrect old password."));
                log.info("changePassword() ---- OLD PASSWORD WRONG");
            }
        }
        if (result.hasErrors()) {
            log.info("result = {}", result);
            return new ModelAndView("password", "passwordDto", passwordDto);
        } else {
            passwordDto.setUsername(principal.getName());
            log.info("changePassword() ---- NO ERRORS {}",passwordDto);
            userService.changePassword(passwordDto);
            return new ModelAndView("successPassword", "passwordDto", passwordDto);
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editForm(WebRequest request, Model model, Principal principal) {
        CasinoUser casinoUser = userService.findCasinoUserByUsername(principal.getName());
        model.addAttribute("casinoUser", casinoUser);
        model.addAttribute("confirmationMessage", null);
        return "edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editInformation(
            @ModelAttribute("casinoUser") CasinoUser casinoUser, Model model, Principal principal) {
        casinoUser.setNickname(principal.getName());
        userService.updateCasinoUserInformation(casinoUser);
        model.addAttribute("casinoUser", casinoUser);
        model.addAttribute("confirmationMessage", "Information updated");
        return "edit";
    }

}
