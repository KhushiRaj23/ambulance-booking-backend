package com.ambulancebooking.ambulance_backend.service.impl;

import com.ambulancebooking.ambulance_backend.dto.UserResponse;
import com.ambulancebooking.ambulance_backend.dto.UserProfileUpdateRequest;
import com.ambulancebooking.ambulance_backend.model.User;
import com.ambulancebooking.ambulance_backend.model.Booking;
import com.ambulancebooking.ambulance_backend.repository.UserRepository;
import com.ambulancebooking.ambulance_backend.repository.BookingRepository;
import com.ambulancebooking.ambulance_backend.service.UserService;
import com.ambulancebooking.ambulance_backend.util.DtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final DtoMapper dtoMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BookingRepository bookingRepository, DtoMapper dtoMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
        this.dtoMapper = dtoMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponse getUserProfile(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return dtoMapper.toUserResponse(user);
    }

    @Override
    public List<Booking> getBookingHistory(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        // You may want to add a method in BookingRepository to find by user
        return bookingRepository.findAll().stream().filter(b -> b.getUser().getId().equals(userId)).toList();
    }

    @Override
    public UserResponse updateUserProfile(Long userId, UserProfileUpdateRequest updateRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        if (updateRequest.getEmail() != null && !updateRequest.getEmail().isBlank()) {
            user.setEmail(updateRequest.getEmail());
        }
        if (updateRequest.getPhone() != null && !updateRequest.getPhone().isBlank()) {
            user.setPhone(updateRequest.getPhone());
        }
        if (updateRequest.getLatitude() != null) {
            user.setLatitude(updateRequest.getLatitude());
        }
        if (updateRequest.getLongitude() != null) {
            user.setLongitude(updateRequest.getLongitude());
        }
        if (updateRequest.getPassword() != null && !updateRequest.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(updateRequest.getPassword()));
        }
        userRepository.save(user);
        return dtoMapper.toUserResponse(user);
    }
} 