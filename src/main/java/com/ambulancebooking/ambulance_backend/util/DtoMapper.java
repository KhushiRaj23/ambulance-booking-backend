package com.ambulancebooking.ambulance_backend.util;

import com.ambulancebooking.ambulance_backend.dto.AuthRequest;
import com.ambulancebooking.ambulance_backend.dto.UserResponse;
import com.ambulancebooking.ambulance_backend.model.User;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {
    public UserResponse toUserResponse(User user) {
        UserResponse dto = new UserResponse();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setLatitude(user.getLatitude());
        dto.setLongitude(user.getLongitude());
        dto.setRole(user.getRole());
        return dto;
    }

    public User toUser(AuthRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        return user;
    }
} 