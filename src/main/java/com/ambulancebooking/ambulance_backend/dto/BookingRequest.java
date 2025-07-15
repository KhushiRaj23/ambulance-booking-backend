package com.ambulancebooking.ambulance_backend.dto;

import com.ambulancebooking.ambulance_backend.enums.BookingType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingRequest {
    private Long hospitalId;
    private Long ambulanceId;
    private BookingType bookingType;
    private PatientDto patient;

    @Getter
    @Setter
    public static class PatientDto {
        private String name;
        private int age;
        private String gender;
        private String condition;
    }
} 