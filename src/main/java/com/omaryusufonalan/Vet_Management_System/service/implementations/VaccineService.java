package com.omaryusufonalan.Vet_Management_System.service.implementations;

import com.omaryusufonalan.Vet_Management_System.dto.request.vaccine.VaccineSaveRequest;
import com.omaryusufonalan.Vet_Management_System.dto.request.vaccine.VaccineUpdateRequest;
import com.omaryusufonalan.Vet_Management_System.dto.response.PageResponse;
import com.omaryusufonalan.Vet_Management_System.dto.response.vaccine.VaccineResponse;
import com.omaryusufonalan.Vet_Management_System.entity.Vaccine;
import com.omaryusufonalan.Vet_Management_System.exception.NotFoundException;
import com.omaryusufonalan.Vet_Management_System.exception.VaccineInEffectException;
import com.omaryusufonalan.Vet_Management_System.mapper.IModelMapperService;
import com.omaryusufonalan.Vet_Management_System.repository.IVaccineRepo;
import com.omaryusufonalan.Vet_Management_System.result.Message;
import com.omaryusufonalan.Vet_Management_System.result.Result;
import com.omaryusufonalan.Vet_Management_System.result.ResultGenerator;
import com.omaryusufonalan.Vet_Management_System.result.ResultWithData;
import com.omaryusufonalan.Vet_Management_System.service.interfaces.IVaccineService;
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
public class VaccineService implements IVaccineService {
    private final IVaccineRepo vaccineRepo;
    private final IModelMapperService modelMapperService;

    @Override
    public ResultWithData<VaccineResponse> save(VaccineSaveRequest vaccineSaveRequest) {

        checkValidityOf(vaccineSaveRequest);

        Vaccine vaccineToBeSaved = this.modelMapperService
                .forRequest()
                .map(vaccineSaveRequest, Vaccine.class);
        vaccineToBeSaved.setId(null);

        this.vaccineRepo.save(vaccineToBeSaved);

        VaccineResponse vaccineResponse = this.modelMapperService
                .forResponse()
                .map(vaccineToBeSaved, VaccineResponse.class);

        return ResultGenerator.generateCreatedFor(vaccineResponse);
    }

    private void checkValidityOf(VaccineSaveRequest vaccineSaveRequest) {
        Optional<Vaccine> optionalVaccine = this.vaccineRepo.checkForVaccineInEffect(
                vaccineSaveRequest.getCode(),
                vaccineSaveRequest.getAnimalId(),
                vaccineSaveRequest.getProtectionStartDate()
        );

        if (optionalVaccine.isPresent())
            throw new VaccineInEffectException(Message.generateVaccineInEffectMessage(vaccineSaveRequest.getAnimalId(), vaccineSaveRequest.getCode()));
    }

    @Override
    public ResultWithData<VaccineResponse> get(Long id) {
        Vaccine vaccineToBeRetrieved = this.vaccineRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(Message.generateNotFoundMessage(id, "Vaccine")));

        VaccineResponse vaccineResponse = this.modelMapperService
                .forResponse()
                .map(vaccineToBeRetrieved, VaccineResponse.class);

        return ResultGenerator.generateOkFor(vaccineResponse);
    }

    @Override
    public ResultWithData<PageResponse<VaccineResponse>> getPage(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Vaccine> vaccinePage = this.vaccineRepo.findAll(pageable);

        Page<VaccineResponse> vaccineResponsePage = vaccinePage
                .map(vaccine -> this.modelMapperService
                        .forResponse()
                        .map(vaccine, VaccineResponse.class));

        return ResultGenerator.generatePageFor(vaccineResponsePage);
    }

    @Override
    public ResultWithData<List<VaccineResponse>> filterByIntervalOfDates(LocalDate start, LocalDate end) {
        List<Vaccine> filteredVaccines = this.vaccineRepo.filterByIntervalOfDates(start, end)
                .orElseThrow(() -> new NotFoundException(Message.generateNotFoundMessage(start, end, "Vaccine")));

        List<VaccineResponse> filteredVaccineResponse = filteredVaccines
                .stream().map(vaccine -> this.modelMapperService
                        .forResponse()
                        .map(vaccine, VaccineResponse.class)).toList();

        return ResultGenerator.generateOkFor(filteredVaccineResponse);
    }

    @Override
    public ResultWithData<VaccineResponse> update(VaccineUpdateRequest vaccineUpdateRequest) {
        this.vaccineRepo.findById(vaccineUpdateRequest.getId())
                .orElseThrow(() -> new NotFoundException(Message.generateNotFoundMessage(vaccineUpdateRequest.getId(), "Vaccine")));

        Vaccine vaccineToBeUpdated = this.modelMapperService
                .forRequest()
                .map(vaccineUpdateRequest, Vaccine.class);

        this.vaccineRepo.save(vaccineToBeUpdated);

        VaccineResponse vaccineResponse = this.modelMapperService
                .forResponse()
                .map(vaccineToBeUpdated, VaccineResponse.class);

        return ResultGenerator.generateOkFor(vaccineResponse);
    }

    @Override
    public Result delete(Long id) {
        Vaccine vaccineToBeDeleted = this.vaccineRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(Message.generateNotFoundMessage(id, "Vaccine")));

        this.vaccineRepo.delete(vaccineToBeDeleted);

        return ResultGenerator.generateOk();
    }
}

