package com.ambulancebooking.ambulance_backend.service.impl;

import com.ambulancebooking.ambulance_backend.model.Hospital;
import com.ambulancebooking.ambulance_backend.model.Ambulance;
import com.ambulancebooking.ambulance_backend.model.Booking;
import com.ambulancebooking.ambulance_backend.service.AdminService;
import com.ambulancebooking.ambulance_backend.repository.HospitalRepository;
import com.ambulancebooking.ambulance_backend.repository.AmbulanceRepository;
import com.ambulancebooking.ambulance_backend.repository.BookingRepository;
import com.ambulancebooking.ambulance_backend.enums.AmbulanceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class AdminServiceImpl implements AdminService {
    private final HospitalRepository hospitalRepository;
    private final AmbulanceRepository ambulanceRepository;
    private final BookingRepository bookingRepository;

    @Autowired
    public AdminServiceImpl(HospitalRepository hospitalRepository, AmbulanceRepository ambulanceRepository, BookingRepository bookingRepository) {
        this.hospitalRepository = hospitalRepository;
        this.ambulanceRepository = ambulanceRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Hospital addHospital(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

    @Override
    public Ambulance addAmbulance(Ambulance ambulance) {
        if (ambulance.getHospital() == null || ambulance.getHospital().getId() == null) {
            throw new RuntimeException("Hospital ID must be provided when adding an ambulance.");
        }
        Long hospitalId = ambulance.getHospital().getId();
        Hospital hospital = hospitalRepository.findById(hospitalId)
            .orElseThrow(() -> new RuntimeException("Hospital not found"));
        ambulance.setHospital(hospital);
        return ambulanceRepository.save(ambulance);
    }

    @Override
    public Ambulance changeAmbulanceStatus(Long ambulanceId, String status) {
        Ambulance ambulance = ambulanceRepository.findById(ambulanceId).orElseThrow(() -> new RuntimeException("Ambulance not found"));
        try {
            ambulance.setStatus(AmbulanceStatus.valueOf(status.trim().toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid ambulance status: " + status + ". Valid values are: AVAILABLE, ON_DUTY, MAINTENANCE");
        }
        return ambulanceRepository.save(ambulance);
    }

    @Override
    public Page<Booking> getAllBookings(Pageable pageable) {
        return bookingRepository.findAll(pageable);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking changeBookingStatus(Long bookingId, String status) {
        Booking booking = bookingRepository.findById(bookingId)
            .orElseThrow(() -> new RuntimeException("Booking not found"));
        try {
            booking.setBookingStatus(com.ambulancebooking.ambulance_backend.enums.BookingStatus.valueOf(status.trim().toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid booking status: " + status + ". Valid values are: ACTIVE, COMPLETED, CANCELLED");
        }
        return bookingRepository.save(booking);
    }

    @Override
    public List<Ambulance> getAllAmbulances() {
        return ambulanceRepository.findAll();
    }

    @Override
    public void removeHospital(Long hospitalId) {
        // First, delete all ambulances for this hospital
        List<Ambulance> ambulances = ambulanceRepository.findByHospitalId(hospitalId);
        ambulanceRepository.deleteAll(ambulances);
        // Now, delete the hospital
        hospitalRepository.deleteById(hospitalId);
    }

    @Override
    public void removeAmbulance(Long ambulanceId) {
        ambulanceRepository.deleteById(ambulanceId);
    }
} 