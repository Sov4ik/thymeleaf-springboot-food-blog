package com.foodblog.controllers;

import com.foodblog.service.AuthService;
import com.foodblog.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private final AuthService authService;

    private final BlogService blogService;

    public AuthController(AuthService authService,
                          BlogService blogService) {
        this.authService = authService;
        this.blogService = blogService;
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                                          @RequestParam String password,
                                          @RequestParam String email) {

		authService.registerUser(username, password, email);

		return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("trending_blogs", blogService.trendingBlogs());
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("trending_blogs", blogService.trendingBlogs());
        return "register";
    }
}
