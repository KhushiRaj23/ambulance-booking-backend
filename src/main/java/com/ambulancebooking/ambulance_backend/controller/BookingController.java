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
import com.ambulancebooking.ambulance_backend.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/book")
    public ResponseEntity<?> bookAmbulance(@RequestBody BookingRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Booking booking = bookingService.bookAmbulance(user.getId(), request);
        return ResponseEntity.ok(booking);
    }

    @GetMapping("/history")
    public ResponseEntity<?> getBookingHistory(@RequestParam Long userId) {
        List<Booking> bookings = bookingService.getBookingHistory(userId);
        return ResponseEntity.ok(bookings);
    }
} 