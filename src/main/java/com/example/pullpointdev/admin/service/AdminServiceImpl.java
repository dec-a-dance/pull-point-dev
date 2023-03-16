package com.example.pullpointdev.admin.service;

import com.example.pullpointdev.admin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
    private final AdminRepository adminRepository;

    public void clean(){
        adminRepository.clean();
    }

}
