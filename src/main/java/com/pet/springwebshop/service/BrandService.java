package com.pet.springwebshop.service;

import com.pet.springwebshop.repo.BrandRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandService {
    private final BrandRepo brandRepo;

    @Autowired
    public BrandService(BrandRepo brandRepo) {
        this.brandRepo = brandRepo;
    }
}
