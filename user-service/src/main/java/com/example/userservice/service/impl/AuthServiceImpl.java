package com.example.userservice.service.impl;

import com.example.userservice.dto.AuthResponse;
import com.example.userservice.dto.LoginRequest;
import com.example.userservice.dto.RegisterRequest;
import com.example.userservice.entity.User;
import com.example.userservice.enums.Role;
import com.example.userservice.event.UserRegisteredEvent;
import com.example.userservice.kafka.UserEventProducer;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.security.JwtService;
import com.example.userservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final UserEventProducer eventProducer;

    @Override
    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("User already exists");
        }

        User user = userMapper.toEntity(request);

        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.ANALYST); // default role
        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);

        eventProducer.sendUserRegisteredEvent(
                UserRegisteredEvent.builder()
                        .userId(user.getId())
                        .email(user.getEmail())
                        .build()
        );
        return AuthResponse.builder()
                .token(jwtService.generateToken(user.getEmail()))
                .build();
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return AuthResponse.builder()
                .token(jwtService.generateToken(user.getEmail()))
                .build();
    }

}
