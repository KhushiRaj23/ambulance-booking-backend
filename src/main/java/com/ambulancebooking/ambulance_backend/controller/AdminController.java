package com.ambulancebooking.ambulance_backend.controller;

import com.ambulancebooking.ambulance_backend.model.Hospital;
import com.ambulancebooking.ambulance_backend.model.Ambulance;
import com.ambulancebooking.ambulance_backend.model.Booking;
import com.ambulancebooking.ambulance_backend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/hospitals/add")
    public Hospital addHospital(@RequestBody Hospital hospital) {
        return adminService.addHospital(hospital);
    }

    @PostMapping("/ambulances/add")
    public Ambulance addAmbulance(@RequestBody Ambulance ambulance) {
        return adminService.addAmbulance(ambulance);
    }

    @PatchMapping("/ambulance/status")
    public Ambulance changeAmbulanceStatus(@RequestParam Long ambulanceId, @RequestParam String status) {
        return adminService.changeAmbulanceStatus(ambulanceId, status);
    }

    @PatchMapping("/bookings/status")
    public Booking changeBookingStatus(@RequestParam Long bookingId, @RequestParam String status) {
        return adminService.changeBookingStatus(bookingId, status);
    }

    @DeleteMapping("/hospitals/remove")
    public void removeHospital(@RequestParam Long hospitalId) {
        adminService.removeHospital(hospitalId);
    }

    @DeleteMapping("/ambulances/remove")
    public void removeAmbulance(@RequestParam Long ambulanceId) {
        adminService.removeAmbulance(ambulanceId);
    }

    @GetMapping("/bookings/all")
    public List<Booking> getAllBookings() {
        return adminService.getAllBookings();
    }

    @GetMapping("/bookings/all/paged")
    public Page<Booking> getAllBookingsPaged(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return adminService.getAllBookings(pageable);
    }

    @GetMapping("/ambulances/all")
    public List<Ambulance> getAllAmbulances() {
        return adminService.getAllAmbulances();
    }
} 