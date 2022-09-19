package com.pet.springwebshop.services;

import com.pet.springwebshop.dto.BrandDTO;
import com.pet.springwebshop.exception.NotFoundException;
import com.pet.springwebshop.entity.Brand;
import com.pet.springwebshop.repo.BrandRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service  @Transactional
public class BrandService {
    private final BrandRepo brandRepo;
    private final MapService mapService;

    @Autowired
    public BrandService(BrandRepo brandRepo, MapService mapService) {
        this.brandRepo = brandRepo;
        this.mapService = mapService;
    }

    public List<BrandDTO> getAll() {
        List<Brand> brands = brandRepo.findAll();
        List<BrandDTO> brandDTOS = new ArrayList<>(brands.size());
        for (Brand brand : brands) {
            brandDTOS.add(mapService.brandEntityToDto(brand));
        }
        return brandDTOS;
    }

    public BrandDTO getBrandById(long id) {
        return mapService.brandEntityToDto(brandRepo.findById(id).
                orElseThrow(() -> new NotFoundException("Brand by id " + id + " wasn't found.")));
    }

    public BrandDTO getBrandByName(String name) {
        return mapService.brandEntityToDto(brandRepo.findBrandByName(name).
                orElseThrow(() -> new NotFoundException("Brand by name " + name + " wasn't found.")));
    }

    public BrandDTO addBrand(BrandDTO brandDTO) {
        return mapService.brandEntityToDto(brandRepo.save(mapService.brandDtoToEntity(brandDTO)));
    }

    public BrandDTO updateBrand(Long id, BrandDTO brandDTO) {
        Brand brand = brandRepo.findById(id).
                orElseThrow(() -> new NotFoundException("Brand by id " + id + " wasn't found."));
        brand.setName(brandDTO.getName());
        brand.setSpecialization(brandDTO.getSpecialization());
        return mapService.brandEntityToDto(brandRepo.save(brand));
    }

    @Transactional
    public BrandDTO deleteBrandById(Long id) {
        Brand brand = brandRepo.findById(id).
                orElseThrow(() -> new NotFoundException("Brand by id " + id + " wasn't found."));
        brandRepo.delete(brand);
        return mapService.brandEntityToDto(brand);
    }

    @Transactional
    public BrandDTO deleteBrandByName(String name) {
        Brand brand = brandRepo.findBrandByName(name).
                orElseThrow(() -> new NotFoundException("Brand by name " + name + " wasn't found."));
        brandRepo.delete(brand);
        return mapService.brandEntityToDto(brand);
    }
}