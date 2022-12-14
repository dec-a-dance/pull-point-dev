package com.example.pullpointdev.category.controller;

import com.example.pullpointdev.category.model.Category;
import com.example.pullpointdev.category.service.CategoriesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/category")
@Tag(name="category", description = "Category API")
public class CategoryController {
    private final CategoriesService categoriesService;

    @GetMapping()
    public ResponseEntity<List<Category>> getMainCategories(){
        return ResponseEntity.ok(categoriesService.getMainCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Category>> getAllByParent(@PathVariable long id){
        return ResponseEntity.ok(categoriesService.findByParent(id));
    }
}
