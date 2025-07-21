package com.ambulancebooking.ambulance_backend.repository;

import com.ambulancebooking.ambulance_backend.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Page<Booking> findAll(Pageable pageable);
} 