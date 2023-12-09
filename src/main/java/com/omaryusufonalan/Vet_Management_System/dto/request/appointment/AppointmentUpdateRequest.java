package com.omaryusufonalan.Vet_Management_System.dto.request.appointment;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentUpdateRequest {
    @Positive
    private Long id;

    @Positive
    private Long animalId;

    @Positive
    private Long doctorId;

    @NotNull(message = "Appointment date cannot be null")
    private LocalDateTime appointmentDate;
}
