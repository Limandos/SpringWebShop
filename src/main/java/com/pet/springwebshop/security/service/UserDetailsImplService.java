package com.pet.springwebshop.security.service;

import com.pet.springwebshop.entity.Customer;
import com.pet.springwebshop.exception.NotFoundException;
import com.pet.springwebshop.repo.CustomerRepo;
import com.pet.springwebshop.services.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsImplService implements UserDetailsService {
    private final CustomerRepo customerRepo;

    @Autowired
    public UserDetailsImplService(CustomerRepo customerRepo, MapService mapService) {
        this.customerRepo = customerRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Customer customer = customerRepo.findCustomerByEmail(userEmail).
                orElseThrow(() -> new NotFoundException("Customer by email " + userEmail + " wasn't found."));
        UserDetails user = User
                .withUsername(customer.getEmail())
                .password(customer.getPasswordHash())
                .roles(customer.getRole())
                .build();
        return user;
    }
}