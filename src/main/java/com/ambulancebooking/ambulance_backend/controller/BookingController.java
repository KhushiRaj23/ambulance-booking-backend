package com.ambulancebooking.ambulance_backend.controller;

import com.ambulancebooking.ambulance_backend.dto.BookingRequest;
import com.ambulancebooking.ambulance_backend.model.Booking;
import com.ambulancebooking.ambulance_backend.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.ambulancebooking.ambulance_backend.repository.UserRepository;
import com.ambulancebooking.ambulance_backend.model.User;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/book")
    public ResponseEntity<?> bookAmbulance(@RequestBody BookingRequest request) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
            Booking booking = bookingService.bookAmbulance(user.getId(), request);
            return ResponseEntity.ok(booking);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/history")
    public ResponseEntity<?> getBookingHistory(@RequestParam Long userId) {
        try {
            List<Booking> bookings = bookingService.getBookingHistory(userId);
            return ResponseEntity.ok(bookings);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
} 