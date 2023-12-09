package com.omaryusufonalan.Vet_Management_System.dto.response.doctor;

import com.omaryusufonalan.Vet_Management_System.dto.response.appointment.AppointmentResponse;
import com.omaryusufonalan.Vet_Management_System.dto.response.availableDate.AvailableDateResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponse {
    private String name;
    private String phone;
    private String mail;
    private String address;
    private String city;
    private List<AvailableDateResponse> availableDates;
    private List<AppointmentResponse> appointments;
}
