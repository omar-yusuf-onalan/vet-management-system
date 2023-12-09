package com.omaryusufonalan.Vet_Management_System.service.implementations;

import com.omaryusufonalan.Vet_Management_System.dto.request.animal.AnimalSaveRequest;
import com.omaryusufonalan.Vet_Management_System.dto.request.animal.AnimalUpdateRequest;
import com.omaryusufonalan.Vet_Management_System.dto.response.PageResponse;
import com.omaryusufonalan.Vet_Management_System.dto.response.animal.AnimalResponse;
import com.omaryusufonalan.Vet_Management_System.entity.Animal;
import com.omaryusufonalan.Vet_Management_System.exception.NotFoundException;
import com.omaryusufonalan.Vet_Management_System.mapper.IModelMapperService;
import com.omaryusufonalan.Vet_Management_System.repository.IAnimalRepo;
import com.omaryusufonalan.Vet_Management_System.result.Message;
import com.omaryusufonalan.Vet_Management_System.result.Result;
import com.omaryusufonalan.Vet_Management_System.result.ResultGenerator;
import com.omaryusufonalan.Vet_Management_System.result.ResultWithData;
import com.omaryusufonalan.Vet_Management_System.service.interfaces.IAnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalService implements IAnimalService {
    private final IAnimalRepo animalRepo;
    private final IModelMapperService modelMapperService;

    @Override
    public ResultWithData<AnimalResponse> save(AnimalSaveRequest animalSaveRequest) {
        Animal animalToBeSaved = this.modelMapperService
                .forRequest()
                .map(animalSaveRequest, Animal.class);
        animalToBeSaved.setId(null);

        this.animalRepo.save(animalToBeSaved);

        AnimalResponse animalResponse = this.modelMapperService
                .forResponse()
                .map(animalToBeSaved, AnimalResponse.class);

        return ResultGenerator.generateCreatedFor(animalResponse);
    }

    @Override
    public ResultWithData<AnimalResponse> get(Long id) {
        Animal animalToBeRetrieved = this.animalRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(Message.generateNotFoundMessage(id, "Animal")));

        AnimalResponse animalResponse = this.modelMapperService
                .forResponse()
                .map(animalToBeRetrieved, AnimalResponse.class);

        return ResultGenerator.generateOkFor(animalResponse);
    }

    @Override
    public ResultWithData<PageResponse<AnimalResponse>> getPage(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Animal> animalPage = this.animalRepo.findAll(pageable);

        Page<AnimalResponse> animalResponsePage = animalPage
                .map(animal -> this.modelMapperService
                        .forResponse()
                        .map(animal, AnimalResponse.class));

        return ResultGenerator.generatePageFor(animalResponsePage);
    }

    @Override
    public ResultWithData<List<AnimalResponse>> filterByAnimalName(String animalName) {
        List<Animal> filteredAnimals = this.animalRepo.filterByName(animalName)
                .orElseThrow(() -> new NotFoundException(Message.generateNotFoundMessage(animalName, "Animal")));

        List<AnimalResponse> filteredAnimalResponse = filteredAnimals
                .stream().map(animal -> this.modelMapperService
                        .forResponse()
                        .map(animal, AnimalResponse.class)).toList();

        return ResultGenerator.generateOkFor(filteredAnimalResponse);
    }

    @Override
    public ResultWithData<AnimalResponse> update(AnimalUpdateRequest animalUpdateRequest) {
        this.animalRepo.findById(animalUpdateRequest.getId())
                .orElseThrow(() -> new NotFoundException(Message.generateNotFoundMessage(animalUpdateRequest.getId(), "Animal")));

        Animal animalToBeUpdated = this.modelMapperService
                .forRequest()
                .map(animalUpdateRequest, Animal.class);

        this.animalRepo.save(animalToBeUpdated);

        AnimalResponse animalResponse = this.modelMapperService
                .forResponse()
                .map(animalToBeUpdated, AnimalResponse.class);

        return ResultGenerator.generateOkFor(animalResponse);
    }

    @Override
    public Result delete(Long id) {
        Animal animalToBeDeleted = this.animalRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(Message.generateNotFoundMessage(id, "Animal")));

        this.animalRepo.delete(animalToBeDeleted);

        return ResultGenerator.generateOk();
    }
}

