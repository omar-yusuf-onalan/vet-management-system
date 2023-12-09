package com.omaryusufonalan.Vet_Management_System.repository;

import com.omaryusufonalan.Vet_Management_System.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IVaccineRepo extends JpaRepository<Vaccine, Long> {
    @Query(value = "SELECT * FROM vaccines WHERE vaccine_code = ?1 AND vaccine_animal_id = ?2" +
            " AND vaccine_protection_finish_date > ?3",
            nativeQuery = true)
    Optional<Vaccine> checkForVaccineInEffect(String code, Long animalId, LocalDate protectionStartDate);

    @Query(value = "SELECT * FROM vaccines WHERE vaccine_protection_finish_date BETWEEN ?1 AND ?2", nativeQuery = true)
    Optional<List<Vaccine>> filterByIntervalOfDates(LocalDate start, LocalDate end);
}