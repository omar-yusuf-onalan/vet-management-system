package com.omaryusufonalan.Vet_Management_System.result;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Message {
    public static final String CREATED = "Data added";
    public static final String OK = "Operation successful";
    public static final String DELETED = "Data deleted";
    public static final String UPDATED = "Data updated";
    public static final String VALIDATION_ERROR = "Data validation error";
    public static final String NOT_FOUND = "Data not found";
    public static final String DATA_INTEGRITY_VIOLATION = "You have tried entering already existing unique information";

    public static String generateNotFoundMessage(Long entityId, String entityName) {
        return entityName + " with ID " + entityId + " not found";
    }

    public static String generateNotFoundMessage(String name, String entityName) {
        return entityName + " with name " + name + " not found";
    }

    public static String generateNotFoundMessage(LocalDate start, LocalDate end, String entityName) {
        return entityName + " within the interval of " + start + " AND " + end + " could not be found";
    }

    public static String generateNotFoundMessage(LocalDate start, LocalDate end, String doctorName, String animalName) {
        String output = "Appointment";

        if (!(start == null && end == null))
            output += " within the interval of " + start + " AND " + end;

        if (!(doctorName == null || doctorName.isEmpty()))
            output += " and with the doctor named " + doctorName;

        if (!(animalName == null || animalName.isEmpty()))
            output += " and with the animal named " + animalName;

        return output + " could not be found";
    }

    public static String generateVaccineInEffectMessage(Long animalId, String vaccineCode) {
        return "Animal with ID " + animalId + " cannot be vaccinated with vaccine code " + vaccineCode + " because the previous vaccine is still in effect";
    }

    public static String generateDoctorNotAvailableMessage(Long doctorId, LocalDate requestedDate) {
        return "Doctor with ID " + doctorId + " is not available on " + requestedDate;
    }

    public static String generateAppointmentHourConflictMessage(Long doctorId, LocalDateTime requestedAppointment) {
        return "Doctor with ID " + doctorId + " is not available at " + requestedAppointment.getHour() + " because of an hour conflict";
    }

    public static String generateIncompleteDateIntervalMessage(String nullDate) {
        return "When filtering appointments with an interval of dates, both date values must be inputted. '" + nullDate + "' is null";
    }
}
