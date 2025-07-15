package com.ambulancebooking.ambulance_backend.repository;

import com.ambulancebooking.ambulance_backend.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
} 