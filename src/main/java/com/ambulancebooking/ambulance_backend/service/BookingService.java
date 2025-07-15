package com.ambulancebooking.ambulance_backend.service;

import com.ambulancebooking.ambulance_backend.dto.BookingRequest;
import com.ambulancebooking.ambulance_backend.model.Booking;
import java.util.List;

public interface BookingService {
    Booking bookAmbulance(Long userId, BookingRequest request);
    List<Booking> getBookingHistory(Long userId);
} 