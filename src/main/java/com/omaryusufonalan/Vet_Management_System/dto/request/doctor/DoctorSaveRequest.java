package com.omaryusufonalan.Vet_Management_System.dto.request.doctor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorSaveRequest {
    @NotNull(message = "Doctor name cannot be null")
    @NotEmpty(message = "Doctor name cannot be empty")
    private String name;

    @NotNull(message = "Doctor phone cannot be null")
    @NotEmpty(message = "Doctor phone cannot be empty")
    private String phone;

    @Email(message = "Not a valid email address")
    private String mail;

    @NotNull(message = "Doctor address cannot be null")
    @NotEmpty(message = "Doctor address cannot be empty")
    private String address;

    @NotNull(message = "Doctor city cannot be null")
    @NotEmpty(message = "Doctor city cannot be empty")
    private String city;
}
