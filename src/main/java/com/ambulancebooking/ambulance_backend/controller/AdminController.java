package com.ambulancebooking.ambulance_backend.controller;

import com.ambulancebooking.ambulance_backend.model.Hospital;
import com.ambulancebooking.ambulance_backend.model.Ambulance;
import com.ambulancebooking.ambulance_backend.model.Booking;
import com.ambulancebooking.ambulance_backend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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

    @GetMapping("/bookings/all")
    public List<Booking> getAllBookings() {
        return adminService.getAllBookings();
    }
} 