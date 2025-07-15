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
        return ambulanceRepository.save(ambulance);
    }

    @Override
    public Ambulance changeAmbulanceStatus(Long ambulanceId, String status) {
        Ambulance ambulance = ambulanceRepository.findById(ambulanceId).orElseThrow(() -> new RuntimeException("Ambulance not found"));
        ambulance.setStatus(AmbulanceStatus.valueOf(status));
        return ambulanceRepository.save(ambulance);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
} 