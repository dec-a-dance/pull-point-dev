package com.example.pullpointdev.admin.controller;

import com.example.pullpointdev.admin.service.AdminServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@Tag(name = "admin", description = "Admin requests")
public class AdminController {
    private final AdminServiceImpl adminServiceImpl;

    @PostMapping("/clean-db")
    public ResponseEntity<String> clean(){
        adminServiceImpl.clean();
        return ResponseEntity.ok("cleaned");
    }
}
