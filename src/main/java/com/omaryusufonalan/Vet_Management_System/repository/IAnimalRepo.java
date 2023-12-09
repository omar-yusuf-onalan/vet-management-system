package com.omaryusufonalan.Vet_Management_System.repository;

import com.omaryusufonalan.Vet_Management_System.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAnimalRepo extends JpaRepository<Animal, Long> {
    @Query(value = "SELECT * FROM animals WHERE animal_name ILIKE %?1%", nativeQuery = true)
    Optional<List<Animal>> filterByName(String name);
}