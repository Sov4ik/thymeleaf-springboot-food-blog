package com.foodblog.service;

import com.foodblog.models.Blog;
import com.foodblog.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.stream.Collectors;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository){
        this.blogRepository = blogRepository;
    }

    public LinkedList<Blog> allBlogs(){
        return blogRepository.findAll();
    }

    public LinkedList<Blog> sliderBlogs(){
        return blogRepository.findAll().stream().limit(5).collect(Collectors.toCollection(LinkedList::new));
    }

    public LinkedList<Blog> trendingBlogs(){
        return blogRepository.findAll().stream().limit(6).collect(Collectors.toCollection(LinkedList::new));
    }

    public LinkedList<Blog> categoryBlogs(){
        return blogRepository.findAll().stream().limit(3).collect(Collectors.toCollection(LinkedList::new));
    }

    public LinkedList<Blog> bigPost(){
        return blogRepository.findAll().stream().limit(1).collect(Collectors.toCollection(LinkedList::new));
    }

    public LinkedList<Blog> simplePost(){
        return blogRepository.findAll().stream().limit(3).collect(Collectors.toCollection(LinkedList::new));
    }

    public LinkedList<Blog> sideBarPosts(){
        return blogRepository.findAll().stream().limit(5).collect(Collectors.toCollection(LinkedList::new));
    }

}
