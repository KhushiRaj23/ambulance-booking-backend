package com.ambulancebooking.ambulance_backend.controller;

import com.ambulancebooking.ambulance_backend.model.Hospital;
import com.ambulancebooking.ambulance_backend.repository.HospitalRepository;
import com.ambulancebooking.ambulance_backend.service.GoogleDistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/hospitals")
public class HospitalController {
    @Autowired
    private HospitalRepository hospitalRepository;
    @Autowired
    private GoogleDistanceService googleDistanceService;

    @GetMapping("/nearest")
    public ResponseEntity<List<HospitalWithDistance>> getNearestHospitals(@RequestParam double lat, @RequestParam double lng) {
        List<Hospital> hospitals = hospitalRepository.findAll();
        List<HospitalWithDistance> result = hospitals.stream()
                .map(h -> new HospitalWithDistance(h, googleDistanceService.calculateDistance(lat, lng, h.getLatitude(), h.getLongitude())))
                .sorted(Comparator.comparingDouble(HospitalWithDistance::getDistance))
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    public static class HospitalWithDistance {
        private Long id;
        private String name;
        private String address;
        private Double latitude;
        private Double longitude;
        private String contactInfo;
        private double distance;

        public HospitalWithDistance(Hospital h, double distance) {
            this.id = h.getId();
            this.name = h.getName();
            this.address = h.getAddress();
            this.latitude = h.getLatitude();
            this.longitude = h.getLongitude();
            this.contactInfo = h.getContactInfo();
            this.distance = distance;
        }
        public Long getId() { return id; }
        public String getName() { return name; }
        public String getAddress() { return address; }
        public Double getLatitude() { return latitude; }
        public Double getLongitude() { return longitude; }
        public String getContactInfo() { return contactInfo; }
        public double getDistance() { return distance; }
    }

    @GetMapping("/all")
    public Page<Hospital> getAllHospitals(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return hospitalRepository.findAll(pageable);
    }
} 