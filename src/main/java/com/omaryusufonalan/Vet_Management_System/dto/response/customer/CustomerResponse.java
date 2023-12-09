package com.omaryusufonalan.Vet_Management_System.dto.response.customer;

import com.omaryusufonalan.Vet_Management_System.dto.response.animal.AnimalResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {
    private String name;
    private String phone;
    private String mail;
    private String address;
    private String city;
    private List<AnimalResponse> animals;
}
