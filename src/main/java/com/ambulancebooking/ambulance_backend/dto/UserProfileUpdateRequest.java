package com.ambulancebooking.ambulance_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileUpdateRequest {
    private String email;
    private String phone;
    private Double latitude;
    private Double longitude;
    private String password; // Optional, if null or blank, do not update
} 