package com.pet.springwebshop.controllers;

import com.pet.springwebshop.dto.BrandDTO;
import com.pet.springwebshop.services.BrandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/brands")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<BrandDTO>> getAll() {
        return new ResponseEntity<>(brandService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandDTO> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(brandService.getBrandById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<BrandDTO> getByName(@RequestParam("name") String name) {
        return new ResponseEntity<>(brandService.getBrandByName(name), HttpStatus.OK);
    }
}