package com.blog.blog_app.repositories;

import com.blog.blog_app.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category , Integer> {
}
