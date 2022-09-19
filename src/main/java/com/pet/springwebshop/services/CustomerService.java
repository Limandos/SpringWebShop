package com.pet.springwebshop.services;

import com.pet.springwebshop.dto.CustomerDTO;
import com.pet.springwebshop.exception.NotFoundException;
import com.pet.springwebshop.entity.Customer;
import com.pet.springwebshop.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service  @Transactional
public class CustomerService {
    private final CustomerRepo customerRepo;
    private final MapService mapService;

    @Autowired
    public CustomerService(CustomerRepo customerRepo, MapService mapService) {
        this.customerRepo = customerRepo;
        this.mapService = mapService;
    }

    public List<CustomerDTO> getAll() {
        List<Customer> customers = customerRepo.findAll();
        List<CustomerDTO> customerDTOS = new ArrayList<>(customers.size());
        for (Customer customer : customers) {
            customerDTOS.add(mapService.customerEntityToDto(customer));
        }
        return customerDTOS;
    }

    public CustomerDTO getCustomerById(long id) {
        return mapService.customerEntityToDto(customerRepo.findById(id).
                orElseThrow(() -> new NotFoundException("Customer by id " + id + " wasn't found.")));
    }

    public CustomerDTO getCustomerByName(String name) {
        return mapService.customerEntityToDto(customerRepo.findCustomerByName(name).
                orElseThrow(() -> new NotFoundException("Customer by name " + name + " wasn't found.")));
    }

    //TODO customer Update
    /*public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer customer = customerRepo.findById(id).
                orElseThrow(() -> new NotFoundException("Customer by name " + id + " wasn't found."));
        customer.setName(customerDTO.getName());
        customer.setSpecialization(customerDTO.getSpecialization());
        return new CustomerDTO(customerRepo.save(customer));
    }*/

    @Transactional
    public CustomerDTO deleteCustomerById(Long id) {
        Customer customer = customerRepo.findById(id).
                orElseThrow(() -> new NotFoundException("Customer by id " + id + " wasn't found."));
        customerRepo.delete(customer);
        return mapService.customerEntityToDto(customer);
    }

    @Transactional
    public CustomerDTO deleteCustomerByName(String name) {
        Customer customer = customerRepo.findCustomerByName(name).
                orElseThrow(() -> new NotFoundException("Customer by name " + name + " wasn't found."));
        customerRepo.delete(customer);
        return mapService.customerEntityToDto(customer);
    }
}