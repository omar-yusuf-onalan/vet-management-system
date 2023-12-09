package com.omaryusufonalan.Vet_Management_System.dto.request.availableDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDateSaveRequest {
    @Positive
    private Long doctorId;

    @NotNull(message = "Doctor's available date cannot be null")
    private LocalDate availableDate;
}
