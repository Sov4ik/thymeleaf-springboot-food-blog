package com.foodblog.service;

import com.foodblog.models.Blog;
import com.foodblog.models.User;
import com.foodblog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;
import java.util.stream.Collectors;

import java.util.UUID;

@Service
public class BlogService {

    @Value("${upload.path}")
    private String uploadPath;

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

    public void addNewBlog(User user,
                           String tittle,
                           String tag,
                           String data,
                           String description,
                           MultipartFile file) throws IOException {

        Blog blog = new Blog();

        blog.setAuthor(user);
        blog.setTittle(tittle);
        blog.setDate(data);
        blog.setTag(tag);
        blog.setDescription(description);

        if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            blog.setFilename(resultFilename);
        }

        blogRepository.save(blog);
    }

}
