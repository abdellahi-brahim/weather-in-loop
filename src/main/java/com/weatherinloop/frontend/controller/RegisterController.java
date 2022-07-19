package com.weatherinloop.frontend.Controller;

import com.weatherinloop.frontend.Model.User;
import com.weatherinloop.frontend.Service.UserService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class RegisterController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/register", method = { RequestMethod.GET })
    public String displayRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register.html";
    }

    @RequestMapping(value = "/createUser", method = { RequestMethod.POST })
    public String createUser(@Validated @ModelAttribute("user") User user, Errors errors) {
        if (errors.hasErrors()) {
            return "register.html";
        }
        boolean isSaved = userService.createNewUser(user);
        if (isSaved) {
            return "redirect:/login?register=true";
        } else {
            return "register.html";
        }
    }

}
