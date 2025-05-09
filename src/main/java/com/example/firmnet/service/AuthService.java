package com.example.firmnet.service;

import com.example.firmnet.dto.AuthRequestDTO;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public boolean login(AuthRequestDTO authRequestDTO) {
        return authRequestDTO.getUsername().equals("admin") && authRequestDTO.getPassword().equals("admin");
    }
}
