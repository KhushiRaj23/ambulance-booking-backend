package com.ambulancebooking.ambulance_backend.model;

import com.ambulancebooking.ambulance_backend.enums.BookingType;
import com.ambulancebooking.ambulance_backend.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ambulance_id")
    private Ambulance ambulance;

    @ManyToOne(optional = false)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingType bookingType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus bookingStatus;

    @Column(nullable = false)
    private LocalDateTime bookingTime;

    @Column
    private LocalDateTime completionTime;

    @Column
    private LocalDateTime cancellationTime;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "patient_id")
    private Patient patient;
} 