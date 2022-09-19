package com.pet.springwebshop.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@DynamicInsert
@DynamicUpdate
public class Customer extends BaseEntity {
    @Column
    private String name;
    @Column(unique = true)
    private String email;
    @Column
    private String role;
    @Column
    private String passwordHash;
    @OneToMany(mappedBy = "customer")
    private List<Ordering> orders;

    public Customer() {
    }

    public Customer(String name, String email, String role, String passwordHash, List<Ordering> orders) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.passwordHash = passwordHash;
        this.orders = orders;
    }

    public String getName() {
        return name;
    }

    public void setName(String userName) {
        name = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}