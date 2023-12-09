package com.omaryusufonalan.Vet_Management_System.service.interfaces;

import com.omaryusufonalan.Vet_Management_System.dto.request.vaccine.VaccineSaveRequest;
import com.omaryusufonalan.Vet_Management_System.dto.request.vaccine.VaccineUpdateRequest;
import com.omaryusufonalan.Vet_Management_System.dto.response.PageResponse;
import com.omaryusufonalan.Vet_Management_System.dto.response.vaccine.VaccineResponse;
import com.omaryusufonalan.Vet_Management_System.result.Result;
import com.omaryusufonalan.Vet_Management_System.result.ResultWithData;

import java.time.LocalDate;
import java.util.List;

public interface IVaccineService {
    ResultWithData<VaccineResponse> save(VaccineSaveRequest vaccineSaveRequest);
    ResultWithData<VaccineResponse> get(Long id);
    ResultWithData<PageResponse<VaccineResponse>> getPage(int page, int pageSize);
    ResultWithData<List<VaccineResponse>> filterByIntervalOfDates(LocalDate start, LocalDate end);
    ResultWithData<VaccineResponse> update(VaccineUpdateRequest vaccineUpdateRequest);
    Result delete(Long id);
}

