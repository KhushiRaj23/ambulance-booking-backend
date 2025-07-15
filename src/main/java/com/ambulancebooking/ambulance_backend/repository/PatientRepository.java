package com.ambulancebooking.ambulance_backend.repository;

import com.ambulancebooking.ambulance_backend.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
} 