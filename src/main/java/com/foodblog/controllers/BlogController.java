package com.foodblog.controllers;

import com.foodblog.models.User;
import com.foodblog.service.BlogService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

        return "index";
    }

    @PostMapping("/add")
    public String addNewBlog(@AuthenticationPrincipal User user,
                             @RequestParam String tittle,
                             @RequestParam String tag,
                             @RequestParam String data,
                             @RequestParam String description,
                             @RequestParam("file") MultipartFile file) throws IOException {

        System.out.println(user);
        blogService.addNewBlog(user, tittle, tag, data, description, file);

        return "redirect:/";
    }

    @GetMapping("/add")
    public String addBlog(){
        return "addBlog";
    }

}
