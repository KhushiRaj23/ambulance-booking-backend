package com.ambulancebooking.ambulance_backend.controller;

import com.ambulancebooking.ambulance_backend.model.Ambulance;
import com.ambulancebooking.ambulance_backend.repository.AmbulanceRepository;
import com.ambulancebooking.ambulance_backend.enums.AmbulanceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ambulances")
public class AmbulanceController {
    @Autowired
    private AmbulanceRepository ambulanceRepository;

    @GetMapping("/available")
    public List<Ambulance> getAvailableAmbulances(@RequestParam Long hospitalId) {
        return ambulanceRepository.findByHospitalIdAndStatus(hospitalId, AmbulanceStatus.AVAILABLE);
    }
} 