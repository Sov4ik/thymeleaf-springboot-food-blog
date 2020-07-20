package com.foodblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/category")
    public String category(){
        return "category";
    }

    @GetMapping("/category-post")
    public String categoryPost(){
        return "category-post";
    }

    @GetMapping("/receipe")
    public String receipe(){
        return "receipe";
    }

    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }
}
