package com.agawrysiuk.casino.controller;

import com.agawrysiuk.casino.model.database.CasinoUser;
import com.agawrysiuk.casino.model.database.CreditCardObject;
import com.agawrysiuk.casino.model.database.PasswordDto;
import com.agawrysiuk.casino.service.UserService;
import com.agawrysiuk.casino.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;

@Slf4j
@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(ViewNames.MAIN)
    public String index() {
        return ViewNames.HOME;
    }

    // Login form
    @RequestMapping(ViewNames.LOGIN)
    public String login() {
        return ViewNames.LOGIN;
    }

    // Login form with error
    @RequestMapping(ViewNames.LOGIN_ERROR)
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return ViewNames.LOGIN;
    }

    // Account page
    @GetMapping(ViewNames.ACCOUNT)
    public String yourAccount(Model model, Principal principal) {
        CasinoUser casinoUser = userService.findCasinoUserByUsername(principal.getName());
        log.info("casinoUser = {}", casinoUser);
        model.addAttribute("balance", String.format("%1$,.2f", casinoUser.getBalance()) + " $");
        model.addAttribute("casinoUser", casinoUser);
        model.addAttribute("message", "You are logged in as " + principal.getName());
        return ViewNames.ACCOUNT;
    }

    @RequestMapping(value = ViewNames.PASSWORD, method = RequestMethod.GET)
    public String showRegistrationForm(WebRequest request, Model model) {
        PasswordDto passwordDto = new PasswordDto();
        model.addAttribute("passwordDto", passwordDto);
        return ViewNames.PASSWORD;
    }

    @RequestMapping(value = ViewNames.PASSWORD, params = "updatePassword", method = RequestMethod.POST)
    public ModelAndView changePassword(
            @ModelAttribute("passwordDto") @Valid PasswordDto passwordDto,
            BindingResult result,
            WebRequest request,
            Errors errors,
            Principal principal) {

        log.info("changePassword() started");
        if (result.hasErrors()) {
            log.info("result = {}", result);
            return new ModelAndView(ViewNames.PASSWORD, "passwordDto", passwordDto);
        }
        if (!userService.doPasswordsMatch(passwordDto.getOldPassword(), principal.getName())) {
            result.addError(new FieldError("passwordDto", "oldPassword", "Incorrect old password."));
            log.info("changePassword() ---- OLD PASSWORD WRONG");
            return new ModelAndView(ViewNames.PASSWORD, "passwordDto", passwordDto);
        }
        passwordDto.setUsername(principal.getName());
        log.info("changePassword() ---- NO ERRORS {}", passwordDto);
        userService.changePassword(passwordDto);
        return new ModelAndView(ViewNames.PASSWORD_SUCCESS, "passwordDto", passwordDto);

    }

    @RequestMapping(value = ViewNames.EDIT, method = RequestMethod.GET)
    public String editForm(WebRequest request, Model model, Principal principal) {
        CasinoUser casinoUser = userService.findCasinoUserByUsername(principal.getName());
        model.addAttribute("casinoUser", casinoUser);
        model.addAttribute("confirmationMessage", null);
        return ViewNames.EDIT;
    }

    @RequestMapping(value = ViewNames.EDIT, method = RequestMethod.POST)
    public String editInformation(
            @ModelAttribute("casinoUser") CasinoUser casinoUser, Model model, Principal principal) {
        casinoUser.setNickname(principal.getName());
        userService.updateCasinoUserInformation(casinoUser);
        model.addAttribute("casinoUser", casinoUser);
        model.addAttribute("confirmationMessage", "Information updated");
        return ViewNames.EDIT;
    }

    @GetMapping(ViewNames.DEPOSIT)
    public String depositPage(Model model, Principal principal) {
        CreditCardObject creditCard = new CreditCardObject();
        model.addAttribute("card", creditCard);
        return ViewNames.DEPOSIT;
    }

    @RequestMapping(value = ViewNames.DEPOSIT, params = "deposit", method = RequestMethod.POST)
    public ModelAndView depositMoney(
            @ModelAttribute("card") @Valid CreditCardObject creditCard,
            BindingResult result,
            WebRequest request,
            Errors errors,
            Principal principal) {

        log.info("depositMoney() started");
        if (result.hasErrors()) {
            log.info("result = {}", result);
            return new ModelAndView("deposit", "card", creditCard);
        } else {
            log.info("depositMoney() ---- NO ERRORS {}", creditCard);
            userService.depositToCasinoUser(Integer.parseInt(creditCard.getDepositAmount()), principal.getName());
            return new ModelAndView(ViewNames.DEPOSIT_SUCCESS);
        }
    }

    @RequestMapping(ViewNames.DEPOSIT_SUCCESS)
    public String successDeposit() {
        return ViewNames.DEPOSIT_SUCCESS;
    }

    @RequestMapping(ViewNames.PASSWORD_SUCCESS)
    public String successPassword() {
        return ViewNames.PASSWORD_SUCCESS;
    }

    @GetMapping(ViewNames.NO_MONEY_PAGE)
    public String noMoneyPage() {
        return ViewNames.NO_MONEY_PAGE;
    }
}
