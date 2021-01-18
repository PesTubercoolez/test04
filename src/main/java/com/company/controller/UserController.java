package com.company.controller;

import com.company.model.User.User;
import com.company.service.EntitiesService.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private UserEntityService entityService;

    @GetMapping("/welcome")
    public String helloUser(Model model) {
        model.addAttribute("data", "Hi there");
        return "/welcome";
    }

    

    @GetMapping("/profile")
    public String profileInfo(Principal principal) {
        User user = entityService.getUserByName(principal.getName());

        return String.format("Hi, %s", user.getName());
    }

    @GetMapping("/registration")
    public String getRegisterPage(Model model){
        model.addAttribute("user", new User());
        return "/registration";
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("user", user);
        //user.setPassword(encoder.encode(user.getPassword()));
        entityService.saveUser(user);
        return "redirect:login";
    }
}
