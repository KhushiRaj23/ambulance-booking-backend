package com.ambulancebooking.ambulance_backend.service;

import com.ambulancebooking.ambulance_backend.dto.UserResponse;
import com.ambulancebooking.ambulance_backend.model.Booking;
import java.util.List;

public interface UserService {
    UserResponse getUserProfile(Long userId);
    List<Booking> getBookingHistory(Long userId);
} 