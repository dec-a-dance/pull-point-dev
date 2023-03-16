package com.example.pullpointdev.category.service;

import com.example.pullpointdev.category.model.Category;
import com.example.pullpointdev.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriesServiceImpl implements CategoriesService{
    private final CategoryRepository categoryRepository;

    public List<Category> getMainCategories(){
        return categoryRepository.findAllByParent(null);
    }

    public List<Category> findByParent(long parent){
        return categoryRepository.findAllByParent(categoryRepository.findById(parent));
    }
}
