package com.foodblog.repository;

import com.foodblog.models.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;

@Repository
public interface BlogRepository extends JpaRepository<Blog, String> {
    LinkedList<Blog> findAll();
}
