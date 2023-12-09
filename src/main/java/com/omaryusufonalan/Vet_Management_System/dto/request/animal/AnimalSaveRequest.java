package com.omaryusufonalan.Vet_Management_System.dto.request.animal;

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
public class AnimalSaveRequest {
    @Positive
    private Long customerId;

    @NotNull(message = "Animal name cannot be null")
    @NotEmpty(message = "Animal name cannot be empty")
    private String name;

    @NotNull(message = "Animal species cannot be null")
    @NotEmpty(message = "Animal species cannot be empty")
    private String species;

    @NotNull(message = "Animal breed cannot be null")
    @NotEmpty(message = "Animal breed cannot be empty")
    private String breed;

    @NotNull(message = "Animal gender cannot be null")
    @NotEmpty(message = "Animal gender cannot be empty")
    private String gender;

    @NotNull(message = "Animal color cannot be null")
    @NotEmpty(message = "Animal color cannot be empty")
    private String color;

    @NotNull(message = "Animal date of birth cannot be null")
    private LocalDate dateOfBirth;
}
