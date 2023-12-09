package com.omaryusufonalan.Vet_Management_System.exception;

import com.omaryusufonalan.Vet_Management_System.result.Message;
import com.omaryusufonalan.Vet_Management_System.result.Result;
import com.omaryusufonalan.Vet_Management_System.result.ResultGenerator;
import com.omaryusufonalan.Vet_Management_System.result.ResultWithData;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Result> handleNotFoundException(NotFoundException e) {
        return new ResponseEntity<>(ResultGenerator.generateNotFoundErrorFor(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(VaccineInEffectException.class)
    public ResponseEntity<Result> handleVaccineInEffectException(VaccineInEffectException e) {
        return new ResponseEntity<>(ResultGenerator.generateVaccineInEffectErrorFor(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DoctorNotAvailableException.class)
    public ResponseEntity<Result> handleDoctorNotAvailableException(DoctorNotAvailableException e) {
        return new ResponseEntity<>(ResultGenerator.generateDoctorNotAvailableErrorFor(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AppointmentHourConflictException.class)
    public ResponseEntity<Result> handleAppointmentHourConflictException(AppointmentHourConflictException e) {
        return new ResponseEntity<>(ResultGenerator.generateAppointmentHourConflictErrorFor(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IncompleteDateIntervalException.class)
    public ResponseEntity<Result> handleIncompleteDateIntervalException(IncompleteDateIntervalException e) {
        return new ResponseEntity<>(ResultGenerator.generateAppointmentHourConflictErrorFor(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultWithData<List<String>>> handleValidationExceptions(MethodArgumentNotValidException e) {
        List<String> validationErrorList = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        return new ResponseEntity<>(ResultGenerator.generateValidationErrorFor(validationErrorList), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Result> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        return new ResponseEntity<>(ResultGenerator.generateDataIntegrityErrorFor(Message.DATA_INTEGRITY_VIOLATION), HttpStatus.BAD_REQUEST);
    }
}
