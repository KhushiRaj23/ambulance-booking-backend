package com.ambulancebooking.ambulance_backend.controller;

import com.ambulancebooking.ambulance_backend.dto.UserResponse;
import com.ambulancebooking.ambulance_backend.dto.UserProfileUpdateRequest;
import com.ambulancebooking.ambulance_backend.model.Booking;
import com.ambulancebooking.ambulance_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.ambulancebooking.ambulance_backend.repository.UserRepository;
import com.ambulancebooking.ambulance_backend.model.User;
import com.ambulancebooking.ambulance_backend.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@RequestParam Long userId) {
        UserResponse response = userService.getUserProfile(userId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(@RequestBody UserProfileUpdateRequest updateRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        UserResponse response = userService.updateUserProfile(user.getId(), updateRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/booking/history")
    public ResponseEntity<?> getBookingHistory() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        List<Booking> bookings = userService.getBookingHistory(user.getId());
        return ResponseEntity.ok(bookings);
    }
} 