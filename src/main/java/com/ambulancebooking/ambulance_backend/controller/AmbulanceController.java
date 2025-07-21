package com.ambulancebooking.ambulance_backend.controller;

import com.ambulancebooking.ambulance_backend.model.Ambulance;
import com.ambulancebooking.ambulance_backend.repository.AmbulanceRepository;
import com.ambulancebooking.ambulance_backend.enums.AmbulanceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/ambulances")
public class AmbulanceController {
    @Autowired
    private AmbulanceRepository ambulanceRepository;

    @GetMapping("/available")
    public List<Ambulance> getAvailableAmbulances(@RequestParam Long hospitalId) {
        return ambulanceRepository.findByHospitalIdAndStatus(hospitalId, AmbulanceStatus.AVAILABLE);
    }

    @GetMapping("/available/paged")
    public Page<Ambulance> getAvailableAmbulancesPaged(@RequestParam Long hospitalId,
                                                      @RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ambulanceRepository.findByHospitalIdAndStatus(hospitalId, AmbulanceStatus.AVAILABLE, pageable);
    }
} 