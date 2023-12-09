package com.omaryusufonalan.Vet_Management_System.dto.response.appointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponse {
    private Long animalId;
    private Long doctorId;
    private LocalDateTime appointmentDate;
}
