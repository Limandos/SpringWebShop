package com.pet.springwebshop.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Brand implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private long Id;
    @Column(unique = true)
    private String Name;
    private String Specialization;

    public Brand() {
    }

    public Brand(long id, String name, String specialization) {
        Id = id;
        Name = name;
        Specialization = specialization;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSpecialization() {
        return Specialization;
    }

    public void setSpecialization(String specialization) {
        Specialization = specialization;
    }
}