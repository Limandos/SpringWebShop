package com.pet.springwebshop.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@DynamicInsert
@DynamicUpdate
public class Brand extends BaseEntity {
    @Column(unique = true)
    private String name;
    @Column
    private String specialization;
    @OneToMany(mappedBy = "brand")
    private List<Product> products;

    public Brand() {
    }

    public Brand(String name, String specialization, List<Product> products) {
        this.name = name;
        this.specialization = specialization;
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}