package com.example.pullpointdev.category.controller;

import com.example.pullpointdev.category.model.Category;
import com.example.pullpointdev.category.service.CategoriesServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
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
    private final CategoriesServiceImpl categoriesServiceImpl;

    @GetMapping()
    @Operation(description = "get all main categories")
    public ResponseEntity<List<Category>> getMainCategories(){
        return ResponseEntity.ok(categoriesServiceImpl.getMainCategories());
    }

    @GetMapping("/{id}")
    @Operation(description = "get all subcategories for certain category")
    public ResponseEntity<List<Category>> getAllByParent(@PathVariable long id){
        return ResponseEntity.ok(categoriesServiceImpl.findByParent(id));
    }
}
