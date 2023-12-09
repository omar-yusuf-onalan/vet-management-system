package com.omaryusufonalan.Vet_Management_System.controller;

import com.omaryusufonalan.Vet_Management_System.dto.request.availableDate.AvailableDateSaveRequest;
import com.omaryusufonalan.Vet_Management_System.dto.request.availableDate.AvailableDateUpdateRequest;
import com.omaryusufonalan.Vet_Management_System.dto.response.PageResponse;
import com.omaryusufonalan.Vet_Management_System.dto.response.availableDate.AvailableDateResponse;
import com.omaryusufonalan.Vet_Management_System.result.Result;
import com.omaryusufonalan.Vet_Management_System.result.ResultWithData;
import com.omaryusufonalan.Vet_Management_System.service.interfaces.IAvailableDateService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vet/v1/availableDates")
public class AvailableDateController {
    private final IAvailableDateService availableDateService;

    public AvailableDateController(IAvailableDateService availableDateService) {
        this.availableDateService = availableDateService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultWithData<AvailableDateResponse> save(@Valid @RequestBody AvailableDateSaveRequest availableDateSaveRequest) {
        return this.availableDateService.save(availableDateSaveRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultWithData<AvailableDateResponse> get(@PathVariable("id") Long id) {
        return this.availableDateService.get(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultWithData<PageResponse<AvailableDateResponse>> getPage(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        return this.availableDateService.getPage(page, pageSize);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultWithData<AvailableDateResponse> update(@Valid @RequestBody AvailableDateUpdateRequest availableDateUpdateRequest) {
        return this.availableDateService.update(availableDateUpdateRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        return this.availableDateService.delete(id);
    }
}
