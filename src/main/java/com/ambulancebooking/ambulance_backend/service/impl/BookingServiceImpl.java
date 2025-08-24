package com.ambulancebooking.ambulance_backend.service.impl;

import com.ambulancebooking.ambulance_backend.dto.BookingRequest;
import com.ambulancebooking.ambulance_backend.enums.AmbulanceStatus;
import com.ambulancebooking.ambulance_backend.enums.BookingStatus;
import com.ambulancebooking.ambulance_backend.enums.BookingType;
import com.ambulancebooking.ambulance_backend.model.*;
import com.ambulancebooking.ambulance_backend.repository.*;
import com.ambulancebooking.ambulance_backend.service.BookingService;
import com.ambulancebooking.ambulance_backend.util.DtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final AmbulanceRepository ambulanceRepository;
    private final HospitalRepository hospitalRepository;
    private final DtoMapper dtoMapper;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, UserRepository userRepository, AmbulanceRepository ambulanceRepository, HospitalRepository hospitalRepository, DtoMapper dtoMapper) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.ambulanceRepository = ambulanceRepository;
        this.hospitalRepository = hospitalRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public Booking bookAmbulance(Long userId, BookingRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Hospital hospital = hospitalRepository.findById(request.getHospitalId()).orElseThrow(() -> new RuntimeException("Hospital not found"));
        Ambulance ambulance = ambulanceRepository.findById(request.getAmbulanceId()).orElseThrow(() -> new RuntimeException("Ambulance not found"));
        if (ambulance.getStatus() != AmbulanceStatus.AVAILABLE) {
            throw new RuntimeException("Ambulance not available");
        }
        // Set ambulance status to ON_DUTY
        ambulance.setStatus(AmbulanceStatus.ON_DUTY);
        ambulanceRepository.save(ambulance);
        // Create patient
        Patient patient = new Patient();
        if (request.getPatient() != null) {
            patient.setName(request.getPatient().getName());
            patient.setAge(request.getPatient().getAge());
            patient.setGender(request.getPatient().getGender());
            patient.setCondition(request.getPatient().getCondition());
        }
        // Create booking
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setAmbulance(ambulance);
        booking.setHospital(hospital);
        booking.setBookingType(request.getBookingType() != null ? request.getBookingType() : BookingType.NORMAL);
        booking.setBookingStatus(BookingStatus.ACTIVE);
        booking.setBookingTime(LocalDateTime.now());
        booking.setPatient(patient);
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getBookingHistory(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return bookingRepository.findAll().stream().filter(b -> b.getUser().getId().equals(userId)).toList();
    }

    @Override
    public List<Booking> getActiveBookings(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return bookingRepository.findAll().stream()
                .filter(b -> b.getUser().getId().equals(userId) && b.getBookingStatus() == BookingStatus.ACTIVE)
                .toList();
    }

    @Override
    public Booking completeBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        
        if (booking.getBookingStatus() != BookingStatus.ACTIVE) {
            throw new RuntimeException("Booking is not active");
        }
        
        // Update booking status
        booking.setBookingStatus(BookingStatus.COMPLETED);
        booking.setCompletionTime(LocalDateTime.now());
        
        // Update ambulance status back to AVAILABLE
        Ambulance ambulance = booking.getAmbulance();
        ambulance.setStatus(AmbulanceStatus.AVAILABLE);
        ambulanceRepository.save(ambulance);
        
        return bookingRepository.save(booking);
    }

    @Override
    public Booking cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        
        if (booking.getBookingStatus() != BookingStatus.ACTIVE) {
            throw new RuntimeException("Booking is not active");
        }
        
        // Update booking status
        booking.setBookingStatus(BookingStatus.CANCELLED);
        booking.setCancellationTime(LocalDateTime.now());
        
        // Update ambulance status back to AVAILABLE
        Ambulance ambulance = booking.getAmbulance();
        ambulance.setStatus(AmbulanceStatus.AVAILABLE);
        ambulanceRepository.save(ambulance);
        
        return bookingRepository.save(booking);
    }
} 