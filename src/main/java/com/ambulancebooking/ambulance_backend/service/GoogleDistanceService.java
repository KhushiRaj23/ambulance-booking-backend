package com.ambulancebooking.ambulance_backend.service;

public interface GoogleDistanceService {
    double calculateDistance(double lat1, double lon1, double lat2, double lon2);
} 