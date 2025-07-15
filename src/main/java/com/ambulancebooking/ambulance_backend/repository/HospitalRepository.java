package com.ambulancebooking.ambulance_backend.repository;

import com.ambulancebooking.ambulance_backend.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
} 