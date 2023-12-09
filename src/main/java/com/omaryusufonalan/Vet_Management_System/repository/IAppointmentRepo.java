package com.omaryusufonalan.Vet_Management_System.repository;

import com.omaryusufonalan.Vet_Management_System.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface IAppointmentRepo extends JpaRepository<Appointment, Long> {
    @Query(value = "SELECT * FROM appointments WHERE appointment_date = ?1", nativeQuery = true)
    Optional<Appointment> checkForConflictingAppointmentHours(LocalDateTime appointment);

    @Query(value = "SELECT appointment_id, appointment_date, appointment_animal_id, appointment_doctor_id \n" +
            "FROM appointments\n" +
            "INNER JOIN \n" +
            "\tdoctors ON appointments.appointment_doctor_id = doctors.doctor_id\n" +
            "INNER JOIN\n" +
            "\tanimals ON appointments.appointment_animal_id = animals.animal_id\n" +
            "WHERE \n" +
            "\tappointment_date BETWEEN ?1 AND ?2\n" +
            "AND\n" +
            "\tdoctor_name ILIKE %?3%\n" +
            "AND\n" +
            "\tanimal_name ILIKE %?4%", nativeQuery = true)
    Optional<List<Appointment>> filterAppointmentsByIntervalOfDatesDoctorAndAnimalName(
            LocalDate start,
            LocalDate end,
            String doctorName,
            String animalName);
}