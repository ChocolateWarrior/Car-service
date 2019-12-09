package com.components.controllers;

import com.components.dto.UserDTO;
import com.components.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//Todo implement controller
@Controller
@RequestMapping("/auth")
public class AuthController {

    private UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public boolean authenticate(@RequestParam String username,
                                @RequestParam String password) {
        return userService.logIn(username, password);
    }

    @PostMapping("/register")
    public boolean register(@RequestParam String username,
                            @RequestParam String password) {
        return userService.saveNewUser(new UserDTO(username, password));
    }
}
