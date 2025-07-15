package com.ambulancebooking.ambulance_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
    private String username;
    private String password;
    private String email;
    private String phone;
    private double latitude;
    private double longitude;
} 