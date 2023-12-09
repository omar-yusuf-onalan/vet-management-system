package com.omaryusufonalan.Vet_Management_System.service.interfaces;

import com.omaryusufonalan.Vet_Management_System.dto.request.doctor.DoctorSaveRequest;
import com.omaryusufonalan.Vet_Management_System.dto.request.doctor.DoctorUpdateRequest;
import com.omaryusufonalan.Vet_Management_System.dto.response.PageResponse;
import com.omaryusufonalan.Vet_Management_System.dto.response.doctor.DoctorResponse;
import com.omaryusufonalan.Vet_Management_System.result.Result;
import com.omaryusufonalan.Vet_Management_System.result.ResultWithData;

public interface IDoctorService {
    ResultWithData<DoctorResponse> save(DoctorSaveRequest doctorSaveRequest);
    ResultWithData<DoctorResponse> get(Long id);
    ResultWithData<PageResponse<DoctorResponse>> getPage(int page, int pageSize);
    ResultWithData<DoctorResponse> update(DoctorUpdateRequest doctorUpdateRequest);
    Result delete(Long id);
}

