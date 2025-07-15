package com.ambulancebooking.ambulance_backend.service;

import com.ambulancebooking.ambulance_backend.dto.AuthRequest;
import com.ambulancebooking.ambulance_backend.dto.AuthResponse;

public interface AuthService {
    AuthResponse register(AuthRequest request);
    AuthResponse login(AuthRequest request);
} 