package com.example.pullpointdev.repository;

import com.example.pullpointdev.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);

    Category findById(long id);

    List<Category> findAllByParent(Category parent);
}
