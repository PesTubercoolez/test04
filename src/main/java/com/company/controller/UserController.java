package com.company.controller;

import com.company.model.User.User;
import com.company.service.EntitiesService.UserEntityService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/main")
public class UserController {

    @Autowired
    private UserEntityService entityService;
    @Autowired
    private Gson gson;

    @GetMapping
    public String helloUser() {
        return "Hello User";
    }

    @GetMapping("/profile")
    public String profileInfo(Principal principal) {
        User user = entityService.getUserByName(principal.getName());

        return String.format("Hi, %s", user.getName());
    }

    @GetMapping("/registration")
    public String registerMe() {
        return "this is the registration page";
    }

    @PostMapping(value = "/registration",consumes = "application/json",produces = "application/json")
    public String registerUser(@RequestBody User user) {
        //user.setPassword(encoder.encode(user.getPassword()));
        entityService.saveUser(user);

        return "user was registered";
    }
}
