package com.example.pullpointdev.category.service;

import com.example.pullpointdev.category.model.Category;

import java.util.List;

public interface CategoriesService {
    List<Category> getMainCategories();

    List<Category> findByParent(long parent);
}
