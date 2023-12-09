package com.omaryusufonalan.Vet_Management_System.service.implementations;

import com.omaryusufonalan.Vet_Management_System.dto.request.doctor.DoctorSaveRequest;
import com.omaryusufonalan.Vet_Management_System.dto.request.doctor.DoctorUpdateRequest;
import com.omaryusufonalan.Vet_Management_System.dto.response.PageResponse;
import com.omaryusufonalan.Vet_Management_System.dto.response.doctor.DoctorResponse;
import com.omaryusufonalan.Vet_Management_System.entity.Doctor;
import com.omaryusufonalan.Vet_Management_System.exception.NotFoundException;
import com.omaryusufonalan.Vet_Management_System.mapper.IModelMapperService;
import com.omaryusufonalan.Vet_Management_System.repository.IDoctorRepo;
import com.omaryusufonalan.Vet_Management_System.result.Message;
import com.omaryusufonalan.Vet_Management_System.result.Result;
import com.omaryusufonalan.Vet_Management_System.result.ResultGenerator;
import com.omaryusufonalan.Vet_Management_System.result.ResultWithData;
import com.omaryusufonalan.Vet_Management_System.service.interfaces.IDoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorService implements IDoctorService {
    private final IDoctorRepo doctorRepo;
    private final IModelMapperService modelMapperService;

    @Override
    public ResultWithData<DoctorResponse> save(DoctorSaveRequest doctorSaveRequest) {
        Doctor doctorToBeSaved = this.modelMapperService
                .forRequest()
                .map(doctorSaveRequest, Doctor.class);
        doctorToBeSaved.setId(null);

        Optional<Doctor> duplicateDoctor = this.doctorRepo.findByPhoneOrMail(doctorToBeSaved.getPhone(), doctorToBeSaved.getMail());

        if (duplicateDoctor.isPresent())
            throw new DataIntegrityViolationException(Message.DATA_INTEGRITY_VIOLATION);

        this.doctorRepo.save(doctorToBeSaved);

        DoctorResponse doctorResponse = this.modelMapperService
                .forResponse()
                .map(doctorToBeSaved, DoctorResponse.class);

        return ResultGenerator.generateCreatedFor(doctorResponse);
    }

    @Override
    public ResultWithData<DoctorResponse> get(Long id) {
        Doctor doctorToBeRetrieved = this.doctorRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(Message.generateNotFoundMessage(id, "Doctor")));

        DoctorResponse doctorResponse = this.modelMapperService
                .forResponse()
                .map(doctorToBeRetrieved, DoctorResponse.class);

        return ResultGenerator.generateOkFor(doctorResponse);
    }

    @Override
    public ResultWithData<PageResponse<DoctorResponse>> getPage(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Doctor> doctorPage = this.doctorRepo.findAll(pageable);

        Page<DoctorResponse> doctorResponsePage = doctorPage
                .map(doctor -> this.modelMapperService
                        .forResponse()
                        .map(doctor, DoctorResponse.class));

        return ResultGenerator.generatePageFor(doctorResponsePage);
    }

    @Override
    public ResultWithData<DoctorResponse> update(DoctorUpdateRequest doctorUpdateRequest) {
        this.doctorRepo.findById(doctorUpdateRequest.getId())
                .orElseThrow(() -> new NotFoundException(Message.generateNotFoundMessage(doctorUpdateRequest.getId(), "Doctor")));

        Doctor doctorToBeUpdated = this.modelMapperService
                .forRequest()
                .map(doctorUpdateRequest, Doctor.class);

        Doctor checkDuplicateDoctor = this.doctorRepo.findByPhoneOrMail(doctorToBeUpdated.getPhone(), doctorToBeUpdated.getMail())
                .orElseThrow();

        if (!doctorToBeUpdated.getId().equals(checkDuplicateDoctor.getId()))
            throw new DataIntegrityViolationException(Message.DATA_INTEGRITY_VIOLATION);

        this.doctorRepo.save(doctorToBeUpdated);

        DoctorResponse doctorResponse = this.modelMapperService
                .forResponse()
                .map(doctorToBeUpdated, DoctorResponse.class);

        return ResultGenerator.generateOkFor(doctorResponse);
    }

    @Override
    public Result delete(Long id) {
        Doctor doctorToBeDeleted = this.doctorRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(Message.generateNotFoundMessage(id, "Doctor")));

        this.doctorRepo.delete(doctorToBeDeleted);

        return ResultGenerator.generateOk();
    }
}

