package com.ambulancebooking.ambulance_backend.dto;

import com.ambulancebooking.ambulance_backend.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String phone;
    private double latitude;
    private double longitude;
    private Role role;
} 