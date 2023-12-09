package com.omaryusufonalan.Vet_Management_System.controller;

import com.omaryusufonalan.Vet_Management_System.dto.request.appointment.AppointmentSaveRequest;
import com.omaryusufonalan.Vet_Management_System.dto.request.appointment.AppointmentUpdateRequest;
import com.omaryusufonalan.Vet_Management_System.dto.response.PageResponse;
import com.omaryusufonalan.Vet_Management_System.dto.response.appointment.AppointmentResponse;
import com.omaryusufonalan.Vet_Management_System.result.Result;
import com.omaryusufonalan.Vet_Management_System.result.ResultWithData;
import com.omaryusufonalan.Vet_Management_System.service.interfaces.IAppointmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/vet/v1/appointments")
public class AppointmentController {
    private final IAppointmentService appointmentService;

    public AppointmentController(IAppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultWithData<AppointmentResponse> save(@Valid @RequestBody AppointmentSaveRequest appointmentSaveRequest) {
        return this.appointmentService.save(appointmentSaveRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultWithData<AppointmentResponse> get(@PathVariable("id") Long id) {
        return this.appointmentService.get(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultWithData<PageResponse<AppointmentResponse>> getPage(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        return this.appointmentService.getPage(page, pageSize);
    }

    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    public ResultWithData<List<AppointmentResponse>> getFilteredAppointmentsByIntervalOfDatesDoctorAndAnimalName(
            @RequestParam(name = "start", required = false) LocalDate start,
            @RequestParam(name = "end", required = false) LocalDate end,
            @RequestParam(name = "doctorName", required = false, defaultValue = "%") String doctorName,
            @RequestParam(name = "animalName", required = false, defaultValue = "%") String animalName
    ) {
        return this.appointmentService.filterAppointmentsByIntervalOfDatesDoctorAndAnimalName(
                start,
                end,
                doctorName,
                animalName
        );
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultWithData<AppointmentResponse> update(@Valid @RequestBody AppointmentUpdateRequest appointmentUpdateRequest) {
        return this.appointmentService.update(appointmentUpdateRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        return this.appointmentService.delete(id);
    }
}

