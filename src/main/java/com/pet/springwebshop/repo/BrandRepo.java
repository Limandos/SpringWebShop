package com.pet.springwebshop.repo;

import com.pet.springwebshop.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepo extends JpaRepository<Brand, Long> {

}
