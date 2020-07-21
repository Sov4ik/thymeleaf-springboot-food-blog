package com.foodblog.repository;

import com.foodblog.models.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<Blog, String> {
    LinkedList<Blog> findAll();

    Optional<Blog> findById(long id);

    void deleteById(long id);
}
