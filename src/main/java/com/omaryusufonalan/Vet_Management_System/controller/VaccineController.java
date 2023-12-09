package com.omaryusufonalan.Vet_Management_System.controller;


import com.omaryusufonalan.Vet_Management_System.dto.request.vaccine.VaccineSaveRequest;
import com.omaryusufonalan.Vet_Management_System.dto.request.vaccine.VaccineUpdateRequest;
import com.omaryusufonalan.Vet_Management_System.dto.response.PageResponse;
import com.omaryusufonalan.Vet_Management_System.dto.response.vaccine.VaccineResponse;
import com.omaryusufonalan.Vet_Management_System.result.Result;
import com.omaryusufonalan.Vet_Management_System.result.ResultWithData;
import com.omaryusufonalan.Vet_Management_System.service.interfaces.IVaccineService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/vet/v1/vaccines")
public class VaccineController {
    private final IVaccineService vaccineService;

    public VaccineController(IVaccineService vaccineService) {
        this.vaccineService = vaccineService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultWithData<VaccineResponse> save(@Valid @RequestBody VaccineSaveRequest vaccineSaveRequest) {
        return this.vaccineService.save(vaccineSaveRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultWithData<VaccineResponse> get(@PathVariable("id") Long id) {
        return this.vaccineService.get(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultWithData<PageResponse<VaccineResponse>> getPage(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        return this.vaccineService.getPage(page, pageSize);
    }

    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    public ResultWithData<List<VaccineResponse>> getFilteredVaccinesByIntervalOfDates(
            @RequestParam(name = "start") LocalDate start,
            @RequestParam(name = "end") LocalDate end
    ) {
        return this.vaccineService.filterByIntervalOfDates(start, end);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultWithData<VaccineResponse> update(@Valid @RequestBody VaccineUpdateRequest vaccineUpdateRequest) {
        return this.vaccineService.update(vaccineUpdateRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        return this.vaccineService.delete(id);
    }
}


