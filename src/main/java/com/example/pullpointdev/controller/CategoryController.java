package com.example.pullpointdev.controller;

import com.example.pullpointdev.entity.Category;
import com.example.pullpointdev.service.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoriesService categoriesService;

    @GetMapping("/main")
    public ResponseEntity<List<Category>> getMainCategories(){
        return ResponseEntity.ok(categoriesService.getMainCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Category>> getAllByParent(@PathVariable long id){
        return ResponseEntity.ok(categoriesService.findByParent(id));
    }
}
