package com.pet.springwebshop.dto;

import java.io.Serializable;

public class BrandDTO implements Serializable {
    private Long id;
    private String name;
    private String specialization;

    public BrandDTO() {
    }

    public BrandDTO(Long id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}