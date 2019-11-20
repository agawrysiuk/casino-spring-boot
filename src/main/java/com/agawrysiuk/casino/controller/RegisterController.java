package com.agawrysiuk.casino.controller;

import com.agawrysiuk.casino.model.database.UserDto;
import com.agawrysiuk.casino.service.UserService;
import com.agawrysiuk.casino.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@Controller
public class RegisterController {

    private UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = ViewNames.REGISTER, method = RequestMethod.GET)
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userdto", userDto);
        return ViewNames.REGISTER;
    }

    @RequestMapping(value = ViewNames.REGISTER, params = "register", method = RequestMethod.POST)
    public ModelAndView registerUserAccount(
            @ModelAttribute("userdto") @Valid UserDto userDto,
            BindingResult result,
            WebRequest request,
            Errors errors) {

        log.info("showRegistrationForm() started");
        if (!result.hasErrors()) {
            log.info("showRegistrationForm() ---- NO ERRORS");
            if (userService.findUserByEmail(userDto.getEmail()) != null) {
                result.addError(new FieldError("userDto", "email", "Email already exists."));
                log.info("showRegistrationForm() ---- EMAIL EXISTS");
            }
            if (userService.findUserByUsername(userDto.getUsername()) != null) {
                result.addError(new FieldError("userDto", "username", "Username already exists."));
                log.info("showRegistrationForm() ---- USERNAME EXISTS");
            }
        }
        if (result.hasErrors()) {
            log.info("showRegistrationForm() ---- ERROR {}", userDto);
            log.info("result = {}", result);
            return new ModelAndView(ViewNames.REGISTER, "userdto", userDto);
        } else {
            log.info("showRegistrationForm() ---- NO ERRORS {}", userDto);
            userService.registerNewUserAccount(userDto);
            return new ModelAndView(ViewNames.REGISTER_SUCCESS, "userdto", userDto);
        }
    }

    @RequestMapping(ViewNames.REGISTER_SUCCESS)
    public String successRegister() {
        return ViewNames.REGISTER_SUCCESS;
    }

}
