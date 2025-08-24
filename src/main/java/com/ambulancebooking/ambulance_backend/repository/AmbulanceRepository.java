package com.ambulancebooking.ambulance_backend.repository;

import com.ambulancebooking.ambulance_backend.enums.AmbulanceStatus;
import com.ambulancebooking.ambulance_backend.model.Ambulance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AmbulanceRepository extends JpaRepository<Ambulance, Long> {
    List<Ambulance> findByHospitalIdAndStatus(Long hospitalId, AmbulanceStatus status);
    List<Ambulance> findByHospitalId(Long hospitalId);
    Page<Ambulance> findByHospitalIdAndStatus(Long hospitalId, AmbulanceStatus status, Pageable pageable);
    
    // Count methods for statistics
    long countByHospitalId(Long hospitalId);
    long countByHospitalIdAndStatus(Long hospitalId, AmbulanceStatus status);
} 