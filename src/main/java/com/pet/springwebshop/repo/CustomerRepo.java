package com.pet.springwebshop.repo;

import com.pet.springwebshop.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Optional<Customer> findCustomerByName(String name);;

    Optional<Customer> findCustomerByEmail(String email);

    Optional<Customer> deleteCustomerById(Long Id);

    Optional<Customer> deleteCustomerByName(String name);
}