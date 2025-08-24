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
import org.springframework.http.ResponseEntity;

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

    @GetMapping("/all")
    public List<Ambulance> getAllAmbulances(@RequestParam Long hospitalId) {
        return ambulanceRepository.findByHospitalId(hospitalId);
    }

    @GetMapping("/status/{status}")
    public List<Ambulance> getAmbulancesByStatus(@RequestParam Long hospitalId, @PathVariable AmbulanceStatus status) {
        return ambulanceRepository.findByHospitalIdAndStatus(hospitalId, status);
    }

    @GetMapping("/counts")
    public AmbulanceCounts getAmbulanceCounts(@RequestParam Long hospitalId) {
        long total = ambulanceRepository.countByHospitalId(hospitalId);
        long available = ambulanceRepository.countByHospitalIdAndStatus(hospitalId, AmbulanceStatus.AVAILABLE);
        long onDuty = ambulanceRepository.countByHospitalIdAndStatus(hospitalId, AmbulanceStatus.ON_DUTY);
        long maintenance = ambulanceRepository.countByHospitalIdAndStatus(hospitalId, AmbulanceStatus.MAINTENANCE);
        
        return new AmbulanceCounts(total, available, onDuty, maintenance);
    }

    @GetMapping("/hospital/{hospitalId}")
    public List<Ambulance> getAmbulancesByHospital(@PathVariable Long hospitalId) {
        return ambulanceRepository.findByHospitalId(hospitalId);
    }

    @GetMapping("/{ambulanceId}")
    public ResponseEntity<Ambulance> getAmbulanceById(@PathVariable Long ambulanceId) {
        return ambulanceRepository.findById(ambulanceId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DTO class for ambulance counts
    public static class AmbulanceCounts {
        private long total;
        private long available;
        private long onDuty;
        private long maintenance;

        public AmbulanceCounts(long total, long available, long onDuty, long maintenance) {
            this.total = total;
            this.available = available;
            this.onDuty = onDuty;
            this.maintenance = maintenance;
        }

        // Getters
        public long getTotal() { return total; }
        public long getAvailable() { return available; }
        public long getOnDuty() { return onDuty; }
        public long getMaintenance() { return maintenance; }
    }
} 