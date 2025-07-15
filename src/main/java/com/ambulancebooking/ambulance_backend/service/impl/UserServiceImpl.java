package com.ambulancebooking.ambulance_backend.service.impl;

import com.ambulancebooking.ambulance_backend.dto.UserResponse;
import com.ambulancebooking.ambulance_backend.model.User;
import com.ambulancebooking.ambulance_backend.model.Booking;
import com.ambulancebooking.ambulance_backend.repository.UserRepository;
import com.ambulancebooking.ambulance_backend.repository.BookingRepository;
import com.ambulancebooking.ambulance_backend.service.UserService;
import com.ambulancebooking.ambulance_backend.util.DtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final DtoMapper dtoMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BookingRepository bookingRepository, DtoMapper dtoMapper) {
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
        this.dtoMapper = dtoMapper;
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
} 