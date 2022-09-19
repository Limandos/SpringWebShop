package com.pet.springwebshop.services;

import com.pet.springwebshop.dto.ProductDTO;
import com.pet.springwebshop.exception.NotFoundException;
import com.pet.springwebshop.entity.Product;
import com.pet.springwebshop.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service @Transactional
public class ProductService {
    private final ProductRepo productRepo;
    private final MapService mapService;

    @Autowired
    public ProductService(ProductRepo productRepo, MapService mapService) {
        this.productRepo = productRepo;
        this.mapService = mapService;
    }

    public List<ProductDTO> getAll() {
        List<Product> products = productRepo.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>(products.size());
        for (Product product : products) {
            productDTOS.add(mapService.productEntityToDto(product));
        }
        return productDTOS;
    }

    public ProductDTO getProductById(long id) {
        return mapService.productEntityToDto(productRepo.findById(id).
                orElseThrow(() -> new NotFoundException("Product by id " + id + " wasn't found.")));
    }

    public ProductDTO getProductByName(String name) {
        return mapService.productEntityToDto(productRepo.findProductByName(name).
                orElseThrow(() -> new NotFoundException("Product by name " + name + " wasn't found.")));
    }

    public ProductDTO addProduct(ProductDTO productDTO) {
        return mapService.productEntityToDto(productRepo.save(mapService.productDtoToEntity(productDTO)));
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepo.findById(id).
                orElseThrow(() -> new NotFoundException("Product by id " + id + " wasn't found."));
        product.setName(productDTO.getName());
        product.setProductSerial(productDTO.getProductSerial());
        product.setPrice(productDTO.getPrice());
        //TODO update Brand field in Product
        //product.setBrand();
        return mapService.productEntityToDto(productRepo.save(product));
    }

    @Transactional
    public ProductDTO deleteProductById(Long id) {
        Product product = productRepo.findById(id).
                orElseThrow(() -> new NotFoundException("Product by id " + id + " wasn't found."));
        productRepo.delete(product);
        return mapService.productEntityToDto(product);
    }

    @Transactional
    public ProductDTO deleteProductByName(String name) {
        Product brand = productRepo.findProductByName(name).
                orElseThrow(() -> new NotFoundException("Product by name " + name + " wasn't found."));
        productRepo.delete(brand);
        return mapService.productEntityToDto(brand);
    }
}