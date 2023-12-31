package com.omaryusufonalan.Vet_Management_System.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "available_dates")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "available_date_id")
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "available_date")
    private LocalDate availableDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "available_date_doctor_id", referencedColumnName = "doctor_id")
    private Doctor doctor;
}
