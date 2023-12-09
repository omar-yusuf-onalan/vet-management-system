package com.omaryusufonalan.Vet_Management_System.service.interfaces;

import com.omaryusufonalan.Vet_Management_System.dto.request.appointment.AppointmentSaveRequest;
import com.omaryusufonalan.Vet_Management_System.dto.request.appointment.AppointmentUpdateRequest;
import com.omaryusufonalan.Vet_Management_System.dto.response.PageResponse;
import com.omaryusufonalan.Vet_Management_System.dto.response.appointment.AppointmentResponse;
import com.omaryusufonalan.Vet_Management_System.result.Result;
import com.omaryusufonalan.Vet_Management_System.result.ResultWithData;

import java.time.LocalDate;
import java.util.List;

public interface IAppointmentService {
    ResultWithData<AppointmentResponse> save(AppointmentSaveRequest appointmentSaveRequest);
    ResultWithData<AppointmentResponse> get(Long id);
    ResultWithData<PageResponse<AppointmentResponse>> getPage(int page, int pageSize);
    ResultWithData<List<AppointmentResponse>> filterAppointmentsByIntervalOfDatesDoctorAndAnimalName(
            LocalDate start,
            LocalDate end,
            String doctorName,
            String animalName
    );
    ResultWithData<AppointmentResponse> update(AppointmentUpdateRequest appointmentUpdateRequest);
    Result delete(Long id);
}

