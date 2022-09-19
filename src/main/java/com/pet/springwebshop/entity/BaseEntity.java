package com.pet.springwebshop.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public class BaseEntity implements Serializable {
    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    private Long id;

    public Long getId() {
        return id;
    }
}
