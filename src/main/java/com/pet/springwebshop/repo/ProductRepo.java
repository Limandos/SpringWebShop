package com.pet.springwebshop.repo;

import com.pet.springwebshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    Optional<Product> findProductByName(String name);

    Optional<Product> deleteProductById(Long Id);

    Optional<Product> deleteProductByName(String name);
}