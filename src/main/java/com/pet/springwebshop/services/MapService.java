package com.pet.springwebshop.services;

import com.pet.springwebshop.dto.BrandDTO;
import com.pet.springwebshop.dto.CustomerDTO;
import com.pet.springwebshop.dto.ProductDTO;
import com.pet.springwebshop.entity.Customer;
import com.pet.springwebshop.entity.Role;
import com.pet.springwebshop.exception.NotFoundException;
import com.pet.springwebshop.entity.Brand;
import com.pet.springwebshop.entity.Product;
import com.pet.springwebshop.repo.BrandRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MapService {
    private final BrandRepo brandRepo;

    @Autowired
    public MapService(BrandRepo brandRepo) {
        this.brandRepo = brandRepo;
    }

    public BrandDTO brandEntityToDto(Brand brand) {
        BrandDTO brandDTO = new BrandDTO();
        brandDTO.setId(brand.getId());
        brandDTO.setName(brand.getName());
        brandDTO.setSpecialization(brand.getSpecialization());
        return brandDTO;
    }

    public Brand brandDtoToEntity(BrandDTO brandDTO) {
        Brand brand = new Brand();
        brand.setName(brandDTO.getName());
        brand.setSpecialization(brandDTO.getSpecialization());
        return brand;
    }

    public ProductDTO productEntityToDto(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setProductSerial(product.getProductSerial());
        productDTO.setPrice(product.getPrice());
        productDTO.setName(product.getBrand().getName());
        return productDTO;
    }

    public Product productDtoToEntity(ProductDTO productDTO) {
        Product product = new Product();
        Brand brand = brandRepo.findBrandByName(productDTO.getBrandName())
                .orElseThrow(() -> new NotFoundException("Brand by name " + productDTO.getBrandName() + " wasn't found."));
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setProductSerial(productDTO.getProductSerial());
        return product;
    }

    public CustomerDTO customerEntityToDto(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setRole(customer.getRole());
        return customerDTO;
    }
}