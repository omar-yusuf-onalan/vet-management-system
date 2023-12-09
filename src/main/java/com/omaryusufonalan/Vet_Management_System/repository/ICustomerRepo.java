package com.omaryusufonalan.Vet_Management_System.repository;

import com.omaryusufonalan.Vet_Management_System.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICustomerRepo extends JpaRepository<Customer, Long> {
    Optional<Customer> findByPhoneOrMail(String phone, String mail);

    @Query(value = "SELECT * FROM customers WHERE customer_name ILIKE %?1%", nativeQuery = true)
    Optional<List<Customer>> filterByName(String name);
}