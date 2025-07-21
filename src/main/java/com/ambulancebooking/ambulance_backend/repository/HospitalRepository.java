package com.ambulancebooking.ambulance_backend.repository;

import com.ambulancebooking.ambulance_backend.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
    Page<Hospital> findAll(Pageable pageable);
} 