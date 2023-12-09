package com.omaryusufonalan.Vet_Management_System.controller;

import com.omaryusufonalan.Vet_Management_System.dto.request.animal.AnimalSaveRequest;
import com.omaryusufonalan.Vet_Management_System.dto.request.animal.AnimalUpdateRequest;
import com.omaryusufonalan.Vet_Management_System.dto.response.PageResponse;
import com.omaryusufonalan.Vet_Management_System.dto.response.animal.AnimalResponse;
import com.omaryusufonalan.Vet_Management_System.result.Result;
import com.omaryusufonalan.Vet_Management_System.result.ResultWithData;
import com.omaryusufonalan.Vet_Management_System.service.interfaces.IAnimalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vet/v1/animals")
public class AnimalController {
    private final IAnimalService animalService;

    public AnimalController(IAnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultWithData<AnimalResponse> save(@Valid @RequestBody AnimalSaveRequest animalSaveRequest) {
        return this.animalService.save(animalSaveRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultWithData<AnimalResponse> get(@PathVariable("id") Long id) {
        return this.animalService.get(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultWithData<PageResponse<AnimalResponse>> getPage(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        return this.animalService.getPage(page, pageSize);
    }

    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    public ResultWithData<List<AnimalResponse>> getFilteredAnimalsByName(@RequestParam(name = "name") String animalName) {
        return this.animalService.filterByAnimalName(animalName);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultWithData<AnimalResponse> update(@Valid @RequestBody AnimalUpdateRequest animalUpdateRequest) {
        return this.animalService.update(animalUpdateRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        return this.animalService.delete(id);
    }
}

