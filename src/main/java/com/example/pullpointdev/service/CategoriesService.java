package com.example.pullpointdev.service;

import com.example.pullpointdev.entity.Category;
import com.example.pullpointdev.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriesService {
    private final CategoryRepository categoryRepository;

    public List<Category> getMainCategories(){
        return categoryRepository.findAllByParent(null);
    }

    public List<Category> findByParent(long parent){
        return categoryRepository.findAllByParent(categoryRepository.findById(parent));
    }
}
