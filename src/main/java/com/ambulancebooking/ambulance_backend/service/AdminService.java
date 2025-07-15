package com.ambulancebooking.ambulance_backend.service;

import com.ambulancebooking.ambulance_backend.model.Hospital;
import com.ambulancebooking.ambulance_backend.model.Ambulance;
import com.ambulancebooking.ambulance_backend.model.Booking;
import java.util.List;

public interface AdminService {
    Hospital addHospital(Hospital hospital);
    Ambulance addAmbulance(Ambulance ambulance);
    Ambulance changeAmbulanceStatus(Long ambulanceId, String status);
    List<Booking> getAllBookings();
} 