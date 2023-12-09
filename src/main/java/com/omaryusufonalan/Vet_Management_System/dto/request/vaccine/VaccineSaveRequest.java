package com.omaryusufonalan.Vet_Management_System.dto.request.vaccine;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccineSaveRequest {
    @Positive
    private Long animalId;

    @NotNull(message = "Vaccine name cannot be null")
    @NotEmpty(message = "Vaccine name cannot be empty")
    private String name;

    @NotNull(message = "Vaccine code cannot be null")
    @NotEmpty(message = "Vaccine code cannot be empty")
    private String code;

    @NotNull(message = "Vaccine protection start date cannot be null")
    private LocalDate protectionStartDate;

    @NotNull(message = "Vaccine protection finish date cannot be null")
    private LocalDate protectionFinishDate;
}
