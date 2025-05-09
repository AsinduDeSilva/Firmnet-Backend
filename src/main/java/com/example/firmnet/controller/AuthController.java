package com.example.firmnet.controller;

import com.example.firmnet.dto.AuthRequestDTO;
import com.example.firmnet.dto.CrudResponseDTO;
import com.example.firmnet.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<CrudResponseDTO<?>> login(@RequestBody AuthRequestDTO authRequestDTO){
        boolean isSuccess = authService.login(authRequestDTO);
        return isSuccess ? ResponseEntity.ok(new CrudResponseDTO<>(true, "Login successful"))
                : ResponseEntity.ok(new CrudResponseDTO<>(false, "Login failed"));

    }
}
