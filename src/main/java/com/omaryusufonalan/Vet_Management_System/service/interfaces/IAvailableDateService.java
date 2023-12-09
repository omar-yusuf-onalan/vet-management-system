package com.omaryusufonalan.Vet_Management_System.service.interfaces;

import com.omaryusufonalan.Vet_Management_System.dto.request.availableDate.AvailableDateSaveRequest;
import com.omaryusufonalan.Vet_Management_System.dto.request.availableDate.AvailableDateUpdateRequest;
import com.omaryusufonalan.Vet_Management_System.dto.response.PageResponse;
import com.omaryusufonalan.Vet_Management_System.dto.response.availableDate.AvailableDateResponse;
import com.omaryusufonalan.Vet_Management_System.entity.AvailableDate;
import com.omaryusufonalan.Vet_Management_System.result.Result;
import com.omaryusufonalan.Vet_Management_System.result.ResultWithData;

import java.time.LocalDate;

public interface IAvailableDateService {
    ResultWithData<AvailableDateResponse> save(AvailableDateSaveRequest availableDateSaveRequest);
    ResultWithData<AvailableDateResponse> get(Long id);
    ResultWithData<PageResponse<AvailableDateResponse>> getPage(int page, int pageSize);
    ResultWithData<AvailableDateResponse> update(AvailableDateUpdateRequest availableDateUpdateRequest);
    Result delete(Long id);
    AvailableDate findByDoctorIdAndAvailableDate(Long doctorId, LocalDate requestedDate);
}

