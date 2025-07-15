package com.ambulancebooking.ambulance_backend.repository;

import com.ambulancebooking.ambulance_backend.enums.AmbulanceStatus;
import com.ambulancebooking.ambulance_backend.model.Ambulance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AmbulanceRepository extends JpaRepository<Ambulance, Long> {
    List<Ambulance> findByHospitalIdAndStatus(Long hospitalId, AmbulanceStatus status);
} 