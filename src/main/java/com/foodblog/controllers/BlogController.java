package com.foodblog.controllers;

import com.foodblog.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService){
        this.blogService = blogService;
    }

    @GetMapping("/")
    public String allBlogs(Model model){

        model.addAttribute("slider_blogs", blogService.sliderBlogs());

        model.addAttribute("trending_blogs", blogService.trendingBlogs());

        model.addAttribute("category_blogs", blogService.categoryBlogs());

        model.addAttribute("big_post", blogService.bigPost());

        model.addAttribute("simple_post", blogService.simplePost());

        model.addAttribute("bar_post", blogService.sideBarPosts());

        model.addAttribute("blogs", blogService.allBlogs());
        return "index";
    }

}
