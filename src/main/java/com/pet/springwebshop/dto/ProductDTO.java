package com.pet.springwebshop.dto;

import com.pet.springwebshop.entity.Product;

public class ProductDTO {
    private long id;
    private String name;
    private String productSerial;
    private int price;
    private String brandName;

    public ProductDTO() {
    }

    public ProductDTO(long id, String name, String productSerial, int price, String brandName) {
        this.id = id;
        this.name = name;
        this.productSerial = productSerial;
        this.price = price;
        this.brandName = brandName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductSerial() {
        return productSerial;
    }

    public void setProductSerial(String productSerial) {
        this.productSerial = productSerial;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
