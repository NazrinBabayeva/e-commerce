package com.example.ecommerce.service;

import com.example.ecommerce.model.dto.UserDto;
import com.example.ecommerce.model.entity.User;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDto signup(UserDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        User user = Mapper.toUserEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        return Mapper.toUserDto(userRepository.save(user));
    }

    public UserDto login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return Mapper.toUserDto(user);
    }
}
