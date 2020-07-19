package com.foodblog.controllers;

import com.foodblog.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String login,
                                          @RequestParam String password,
                                          @RequestParam String email) {

		authService.registerUser(login, password, email);

		return "redirect:/";
    }
}
