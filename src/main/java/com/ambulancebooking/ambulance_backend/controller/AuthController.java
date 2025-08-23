package com.ambulancebooking.ambulance_backend.controller;

import com.ambulancebooking.ambulance_backend.dto.AuthRequest;
import com.ambulancebooking.ambulance_backend.dto.AuthResponse;
import com.ambulancebooking.ambulance_backend.model.User;
import com.ambulancebooking.ambulance_backend.repository.UserRepository;
import com.ambulancebooking.ambulance_backend.service.AuthService;
import com.ambulancebooking.ambulance_backend.service.UserService;
import com.ambulancebooking.ambulance_backend.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest request) {
        try {
            AuthResponse response = authService.register(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest loginRequest) {
        AuthResponse authResponse = authService.login(loginRequest); // This returns the token
        Optional<User> userOpt = userRepository.findByUsername(loginRequest.getUsername());
        if (userOpt.isEmpty()) {
            throw new BadRequestException("Invalid username or password");
        }
        User user = userOpt.get();
        Map<String, Object> response = new HashMap<>();
        response.put("token", authResponse.getToken());
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("id", user.getId());
        userMap.put("username", user.getUsername());
        userMap.put("email", user.getEmail());
        userMap.put("role", user.getRole());
        userMap.put("latitude", user.getLatitude());
        userMap.put("longitude", user.getLongitude());
        response.put("user", userMap);
        return ResponseEntity.ok(response);
    }
} 