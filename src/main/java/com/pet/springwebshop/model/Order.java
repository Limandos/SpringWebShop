package com.pet.springwebshop.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Order {
    @Id
    private long Id;
    private long UserId;
}
