package com.omaryusufonalan.Vet_Management_System.dto.response.animal;

import com.omaryusufonalan.Vet_Management_System.dto.response.appointment.AppointmentResponse;
import com.omaryusufonalan.Vet_Management_System.dto.response.vaccine.VaccineResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalResponse {
    private Long customerId;
    private String name;
    private String species;
    private String breed;
    private String gender;
    private String color;
    private LocalDate dateOfBirth;
    private List<VaccineResponse> vaccines;
    private List<AppointmentResponse> appointments;
}
