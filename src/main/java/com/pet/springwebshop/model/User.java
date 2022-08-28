package com.pet.springwebshop.model;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private String UserName;
    @Column(unique = true)
    private String Email;
    private String Role;
    private byte[] PasswordHash;
    private byte[] PasswordSalt;
}