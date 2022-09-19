package com.pet.springwebshop.controllers;

import com.pet.springwebshop.dto.BrandDTO;
import com.pet.springwebshop.dto.CustomerDTO;
import com.pet.springwebshop.dto.ProductDTO;
import com.pet.springwebshop.services.BrandService;
import com.pet.springwebshop.services.CustomerService;
import com.pet.springwebshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final ProductService productService;
    private final BrandService brandService;
    private final CustomerService customerService;

    @Autowired
    public AdminController(ProductService productService, BrandService brandService, CustomerService customerService) {
        this.productService = productService;
        this.brandService = brandService;
        this.customerService = customerService;
    }

    //Products
    @PostMapping("/products/add")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(productService.addProduct(productDTO), HttpStatus.CREATED);
    }

    @PutMapping("/products/update/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@RequestParam Long id, @RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(productService.updateProduct(id, productDTO), HttpStatus.OK);
    }

    @DeleteMapping("/products/delete/{id}")
    public ResponseEntity<ProductDTO> deleteProductById(@PathVariable Long id) {
        return new ResponseEntity<>(productService.deleteProductById(id), HttpStatus.OK);
    }

    @DeleteMapping("/products/delete")
    public ResponseEntity<ProductDTO> deleteProductByName(@RequestParam("name") String name) {
        return new ResponseEntity<>(productService.deleteProductByName(name), HttpStatus.OK);
    }

    //Brands
    @PostMapping("/brands/add")
    public ResponseEntity<BrandDTO> addBrand(@RequestBody BrandDTO brandDTO) {
        return new ResponseEntity<>(brandService.addBrand(brandDTO), HttpStatus.CREATED);
    }

    @PutMapping("/brands/update/{id}")
    public ResponseEntity<BrandDTO> updateBrand(@RequestParam Long id, @RequestBody BrandDTO brandDTO) {
        return new ResponseEntity<>(brandService.updateBrand(id, brandDTO), HttpStatus.OK);
    }

    @DeleteMapping("/brands/delete/{id}")
    public ResponseEntity<BrandDTO> deleteBrandById(@PathVariable Long id) {
        return new ResponseEntity<>(brandService.deleteBrandById(id), HttpStatus.OK);
    }

    @DeleteMapping("/brands/delete")
    public ResponseEntity<BrandDTO> deleteBrandByName(@RequestParam("name") String name) {
        return new ResponseEntity<>(brandService.deleteBrandByName(name), HttpStatus.OK);
    }

    //Customers
    @DeleteMapping("/customers/delete/{id}")
    public ResponseEntity<CustomerDTO> deleteCustomerById(@PathVariable Long id) {
        return new ResponseEntity<>(customerService.deleteCustomerById(id), HttpStatus.OK);
    }

    @DeleteMapping("/customers/delete")
    public ResponseEntity<CustomerDTO> deleteCustomerByName(@RequestParam("name") String name) {
        return new ResponseEntity<>(customerService.deleteCustomerByName(name), HttpStatus.OK);
    }
}