package com.omaryusufonalan.Vet_Management_System.result;

import com.omaryusufonalan.Vet_Management_System.dto.response.PageResponse;
import org.springframework.data.domain.Page;

public class ResultGenerator {
    public static <T> ResultWithData<T> generateCreatedFor(T data) {
        return new ResultWithData<>(true, Message.CREATED, Code.CREATED, data);
    }

    public static <T> ResultWithData<T> generateValidationErrorFor(T data) {
        return new ResultWithData<>(false, Message.VALIDATION_ERROR, Code.BAD_REQUEST, data);
    }

    public static <T> ResultWithData<T> generateOkFor(T data) {
        return new ResultWithData<>(true, Message.OK, Code.OK, data);
    }

    public static Result generateOk() {
        return new Result(true, Message.OK, Code.OK);
    }

    public static Result generateNotFoundErrorFor(String message) {
        return new Result(false, message, Code.NOT_FOUND);
    }

    public static Result generateVaccineInEffectErrorFor(String message) {
        return new Result(false, message, Code.BAD_REQUEST);
    }

    public static Result generateDoctorNotAvailableErrorFor(String message) {
        return new Result(false, message, Code.NOT_FOUND);
    }

    public static Result generateAppointmentHourConflictErrorFor(String message) {
        return new Result(false, message, Code.BAD_REQUEST);
    }

    public static Result generateIncompleteDateIntervalErrorFor(String message) {
        return new Result(false, message, Code.BAD_REQUEST);
    }

    public static Result generateDataIntegrityErrorFor(String message) {
        return new Result(false, message, Code.BAD_REQUEST);
    }

    public static <T> ResultWithData<PageResponse<T>> generatePageFor(Page<T> pageData) {
        PageResponse<T> pageResponse = new PageResponse<>();

        pageResponse.setItems(pageData.getContent());
        pageResponse.setPageNumber(pageData.getNumber());
        pageResponse.setPageSize(pageData.getSize());
        pageResponse.setTotalElements(pageData.getTotalElements());

        return ResultGenerator.generateOkFor(pageResponse);
    }
}
