package com.example.ecommerce.controller;

import com.example.ecommerce.config.JwtUtils;
import com.example.ecommerce.model.dto.UserDto;
import com.example.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtUtils jwtUtils;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody UserDto dto) {
        UserDto savedUser = userService.signup(dto);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDto dto) {
        UserDto user = userService.login(dto.getEmail(), dto.getPassword());
        String token = jwtUtils.generateToken(user.getEmail());
        return ResponseEntity.ok(token);
    }
}
