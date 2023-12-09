package com.omaryusufonalan.Vet_Management_System.service.implementations;

import com.omaryusufonalan.Vet_Management_System.dto.request.availableDate.AvailableDateSaveRequest;
import com.omaryusufonalan.Vet_Management_System.dto.request.availableDate.AvailableDateUpdateRequest;
import com.omaryusufonalan.Vet_Management_System.dto.response.PageResponse;
import com.omaryusufonalan.Vet_Management_System.dto.response.availableDate.AvailableDateResponse;
import com.omaryusufonalan.Vet_Management_System.entity.AvailableDate;
import com.omaryusufonalan.Vet_Management_System.exception.DoctorNotAvailableException;
import com.omaryusufonalan.Vet_Management_System.exception.NotFoundException;
import com.omaryusufonalan.Vet_Management_System.mapper.IModelMapperService;
import com.omaryusufonalan.Vet_Management_System.repository.IAvailableDateRepo;
import com.omaryusufonalan.Vet_Management_System.result.Message;
import com.omaryusufonalan.Vet_Management_System.result.Result;
import com.omaryusufonalan.Vet_Management_System.result.ResultGenerator;
import com.omaryusufonalan.Vet_Management_System.result.ResultWithData;
import com.omaryusufonalan.Vet_Management_System.service.interfaces.IAvailableDateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class AvailableDateService implements IAvailableDateService {
    private final IAvailableDateRepo availableDateRepo;
    private final IModelMapperService modelMapperService;

    @Override
    public ResultWithData<AvailableDateResponse> save(AvailableDateSaveRequest availableDateSaveRequest) {
        AvailableDate availableDateToBeSaved = this.modelMapperService
                .forRequest()
                .map(availableDateSaveRequest, AvailableDate.class);
        availableDateToBeSaved.setId(null);

        this.availableDateRepo.save(availableDateToBeSaved);

        AvailableDateResponse availableDateResponse = this.modelMapperService
                .forResponse()
                .map(availableDateToBeSaved, AvailableDateResponse.class);

        return ResultGenerator.generateCreatedFor(availableDateResponse);
    }

    @Override
    public ResultWithData<AvailableDateResponse> get(Long id) {
        AvailableDate availableDateToBeRetrieved = this.availableDateRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(Message.generateNotFoundMessage(id, "AvailableDate")));

        AvailableDateResponse availableDateResponse = this.modelMapperService
                .forResponse()
                .map(availableDateToBeRetrieved, AvailableDateResponse.class);

        return ResultGenerator.generateOkFor(availableDateResponse);
    }

    @Override
    public ResultWithData<PageResponse<AvailableDateResponse>> getPage(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<AvailableDate> availableDatePage = this.availableDateRepo.findAll(pageable);

        Page<AvailableDateResponse> availableDateResponsePage = availableDatePage
                .map(availableDate -> this.modelMapperService
                        .forResponse()
                        .map(availableDate, AvailableDateResponse.class));

        return ResultGenerator.generatePageFor(availableDateResponsePage);
    }

    @Override
    public ResultWithData<AvailableDateResponse> update(AvailableDateUpdateRequest availableDateUpdateRequest) {
        this.availableDateRepo.findById(availableDateUpdateRequest.getId())
                .orElseThrow(() -> new NotFoundException(Message.generateNotFoundMessage(availableDateUpdateRequest.getId(), "AvailableDate")));

        AvailableDate availableDateToBeUpdated = this.modelMapperService
                .forRequest()
                .map(availableDateUpdateRequest, AvailableDate.class);

        this.availableDateRepo.save(availableDateToBeUpdated);

        AvailableDateResponse availableDateResponse = this.modelMapperService
                .forResponse()
                .map(availableDateToBeUpdated, AvailableDateResponse.class);

        return ResultGenerator.generateOkFor(availableDateResponse);
    }

    @Override
    public Result delete(Long id) {
        AvailableDate availableDateToBeDeleted = this.availableDateRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(Message.generateNotFoundMessage(id, "AvailableDate")));

        this.availableDateRepo.delete(availableDateToBeDeleted);

        return ResultGenerator.generateOk();
    }

    @Override
    public AvailableDate findByDoctorIdAndAvailableDate(Long doctorId, LocalDate requestedDate) {
        return this.availableDateRepo.findByDoctorIdAndAvailableDate(doctorId, requestedDate)
                .orElseThrow(() -> new DoctorNotAvailableException(Message.generateDoctorNotAvailableMessage(doctorId, requestedDate)));
    }
}
