package com.ambulancebooking.ambulance_backend.controller;

import com.ambulancebooking.ambulance_backend.model.Hospital;
import com.ambulancebooking.ambulance_backend.repository.HospitalRepository;
import com.ambulancebooking.ambulance_backend.service.GoogleDistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/hospitals")
public class HospitalController {
    @Autowired
    private HospitalRepository hospitalRepository;
    @Autowired
    private GoogleDistanceService googleDistanceService;

    @GetMapping("/nearest")
    public List<Hospital> getNearestHospitals(@RequestParam double lat, @RequestParam double lng) {
        List<Hospital> hospitals = hospitalRepository.findAll();
        return hospitals.stream()
                .sorted(Comparator.comparingDouble(h -> googleDistanceService.calculateDistance(lat, lng, h.getLatitude(), h.getLongitude())))
                .collect(Collectors.toList());
    }
} 