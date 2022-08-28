package com.pet.springwebshop.model;

import javax.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @Column(unique = true)
    private String Name;
    private String ProductSerial;
    private int Price;
    private long BrandId;
}