package com.omaryusufonalan.Vet_Management_System.repository;

import com.omaryusufonalan.Vet_Management_System.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDoctorRepo extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByPhoneOrMail(String phone, String mail);
}
