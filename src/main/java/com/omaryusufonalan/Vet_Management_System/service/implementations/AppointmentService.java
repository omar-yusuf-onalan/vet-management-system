package com.omaryusufonalan.Vet_Management_System.service.implementations;

import com.omaryusufonalan.Vet_Management_System.dto.request.appointment.AppointmentSaveRequest;
import com.omaryusufonalan.Vet_Management_System.dto.request.appointment.AppointmentUpdateRequest;
import com.omaryusufonalan.Vet_Management_System.dto.response.PageResponse;
import com.omaryusufonalan.Vet_Management_System.dto.response.appointment.AppointmentResponse;
import com.omaryusufonalan.Vet_Management_System.entity.Appointment;
import com.omaryusufonalan.Vet_Management_System.entity.AvailableDate;
import com.omaryusufonalan.Vet_Management_System.exception.AppointmentHourConflictException;
import com.omaryusufonalan.Vet_Management_System.exception.IncompleteDateIntervalException;
import com.omaryusufonalan.Vet_Management_System.exception.NotFoundException;
import com.omaryusufonalan.Vet_Management_System.mapper.IModelMapperService;
import com.omaryusufonalan.Vet_Management_System.repository.IAppointmentRepo;
import com.omaryusufonalan.Vet_Management_System.result.Message;
import com.omaryusufonalan.Vet_Management_System.result.Result;
import com.omaryusufonalan.Vet_Management_System.result.ResultGenerator;
import com.omaryusufonalan.Vet_Management_System.result.ResultWithData;
import com.omaryusufonalan.Vet_Management_System.service.interfaces.IAppointmentService;
import com.omaryusufonalan.Vet_Management_System.service.interfaces.IAvailableDateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentService implements IAppointmentService {
    private final IAppointmentRepo appointmentRepo;
    private final IAvailableDateService availableDateService;
    private final IModelMapperService modelMapperService;

    @Override
    public ResultWithData<AppointmentResponse> save(AppointmentSaveRequest appointmentSaveRequest) {
        checkValidityOf(appointmentSaveRequest);

        Appointment appointmentToBeSaved = this.modelMapperService
                .forRequest()
                .map(appointmentSaveRequest, Appointment.class);
        appointmentToBeSaved.setId(null);

        this.appointmentRepo.save(appointmentToBeSaved);

        AppointmentResponse appointmentResponse = this.modelMapperService
                .forResponse()
                .map(appointmentToBeSaved, AppointmentResponse.class);

        return ResultGenerator.generateCreatedFor(appointmentResponse);
    }

    private void checkValidityOf(AppointmentSaveRequest appointmentSaveRequest) {
        AvailableDate checkIfThereIsAvailableDoctor = this.availableDateService.findByDoctorIdAndAvailableDate(
                appointmentSaveRequest.getDoctorId(),
                appointmentSaveRequest.getAppointmentDate().toLocalDate()
        );

        Optional<Appointment> hourConflict = this.appointmentRepo.checkForConflictingAppointmentHours(
                appointmentSaveRequest.getAppointmentDate()
        );

        if (hourConflict.isPresent())
            throw new AppointmentHourConflictException(Message.generateAppointmentHourConflictMessage(
                    appointmentSaveRequest.getDoctorId(),
                    appointmentSaveRequest.getAppointmentDate()
            ));
    }

    @Override
    public ResultWithData<AppointmentResponse> get(Long id) {
        Appointment appointmentToBeRetrieved = this.appointmentRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(Message.generateNotFoundMessage(id, "Appointment")));

        AppointmentResponse appointmentResponse = this.modelMapperService
                .forResponse()
                .map(appointmentToBeRetrieved, AppointmentResponse.class);

        return ResultGenerator.generateOkFor(appointmentResponse);
    }

    @Override
    public ResultWithData<PageResponse<AppointmentResponse>> getPage(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Appointment> appointmentPage = this.appointmentRepo.findAll(pageable);

        Page<AppointmentResponse> appointmentResponsePage = appointmentPage
                .map(appointment -> this.modelMapperService
                        .forResponse()
                        .map(appointment, AppointmentResponse.class));

        return ResultGenerator.generatePageFor(appointmentResponsePage);
    }

    @Override
    public ResultWithData<List<AppointmentResponse>> filterAppointmentsByIntervalOfDatesDoctorAndAnimalName(
            LocalDate start, LocalDate end, String doctorName, String animalName
    ) {
        if ((start == null && end != null) || (start != null && end == null)) {
           String nullDate = start == null ? "Start" : "End";

           throw new IncompleteDateIntervalException(Message.generateIncompleteDateIntervalMessage(nullDate));
        }

        if (start == null) {
            start = LocalDate.of(1970, 1, 1);
            end = LocalDate.now().plusYears(10);
        }

        Optional<List<Appointment>> optionalFilteredAppointments = this.appointmentRepo.filterAppointmentsByIntervalOfDatesDoctorAndAnimalName(
                start,
                end,
                doctorName,
                animalName
        );

        List<Appointment> filteredAppointments = optionalFilteredAppointments.orElseThrow();

        List<AppointmentResponse> filteredAppointmentResponse = filteredAppointments
                .stream().map(appointment -> this.modelMapperService
                        .forResponse()
                        .map(appointment, AppointmentResponse.class)).toList();

        return ResultGenerator.generateOkFor(filteredAppointmentResponse);
    }

    @Override
    public ResultWithData<AppointmentResponse> update(AppointmentUpdateRequest appointmentUpdateRequest) {
        this.appointmentRepo.findById(appointmentUpdateRequest.getId())
                .orElseThrow(() -> new NotFoundException(Message.generateNotFoundMessage(appointmentUpdateRequest.getId(), "Appointment")));

        Appointment appointmentToBeUpdated = this.modelMapperService
                .forRequest()
                .map(appointmentUpdateRequest, Appointment.class);

        this.appointmentRepo.save(appointmentToBeUpdated);

        AppointmentResponse appointmentResponse = this.modelMapperService
                .forResponse()
                .map(appointmentToBeUpdated, AppointmentResponse.class);

        return ResultGenerator.generateOkFor(appointmentResponse);
    }

    @Override
    public Result delete(Long id) {
        Appointment appointmentToBeDeleted = this.appointmentRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(Message.generateNotFoundMessage(id, "Appointment")));

        this.appointmentRepo.delete(appointmentToBeDeleted);

        return ResultGenerator.generateOk();
    }
}


