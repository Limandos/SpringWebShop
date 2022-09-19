package com.pet.springwebshop.repo;

import com.pet.springwebshop.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepo extends JpaRepository<Brand, Long> {
    Optional<Brand> findBrandByName(String name);

    Optional<Brand> deleteBrandById(Long Id);

    Optional<Brand> deleteBrandByName(String name);
}