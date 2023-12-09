package com.omaryusufonalan.Vet_Management_System.service.interfaces;

import com.omaryusufonalan.Vet_Management_System.dto.request.animal.AnimalSaveRequest;
import com.omaryusufonalan.Vet_Management_System.dto.request.animal.AnimalUpdateRequest;
import com.omaryusufonalan.Vet_Management_System.dto.response.PageResponse;
import com.omaryusufonalan.Vet_Management_System.dto.response.animal.AnimalResponse;
import com.omaryusufonalan.Vet_Management_System.result.Result;
import com.omaryusufonalan.Vet_Management_System.result.ResultWithData;

import java.util.List;

public interface IAnimalService {
    ResultWithData<AnimalResponse> save(AnimalSaveRequest animalSaveRequest);
    ResultWithData<AnimalResponse> get(Long id);
    ResultWithData<PageResponse<AnimalResponse>> getPage(int page, int pageSize);
    ResultWithData<List<AnimalResponse>> filterByAnimalName(String animalName);
    ResultWithData<AnimalResponse> update(AnimalUpdateRequest animalUpdateRequest);
    Result delete(Long id);
}

