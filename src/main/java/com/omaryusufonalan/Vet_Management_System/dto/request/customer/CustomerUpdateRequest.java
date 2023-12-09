package com.omaryusufonalan.Vet_Management_System.dto.request.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerUpdateRequest {
    @Positive
    private Long id;

    @NotNull(message = "Customer name cannot be null")
    @NotEmpty(message = "Customer name cannot be empty")
    private String name;

    @NotNull(message = "Customer phone cannot be null")
    @NotEmpty(message = "Customer phone cannot be empty")
    private String phone;

    @Email(message = "Not a valid email address")
    private String mail;

    @NotNull(message = "Customer address cannot be null")
    @NotEmpty(message = "Customer address cannot be empty")
    private String address;

    @NotNull(message = "Customer city cannot be null")
    @NotEmpty(message = "Customer city cannot be empty")
    private String city;
}
