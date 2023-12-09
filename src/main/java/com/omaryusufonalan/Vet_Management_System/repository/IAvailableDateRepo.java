package com.omaryusufonalan.Vet_Management_System.repository;

import com.omaryusufonalan.Vet_Management_System.entity.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface IAvailableDateRepo extends JpaRepository<AvailableDate, Long> {
    @Query(value = "SELECT * FROM available_dates WHERE available_date_doctor_id = ?1 AND " +
            "available_date = ?2", nativeQuery = true)
    Optional<AvailableDate> findByDoctorIdAndAvailableDate(Long doctorId, LocalDate availableDate);
}