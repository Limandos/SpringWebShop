package com.pet.springwebshop.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@DynamicInsert
@DynamicUpdate
public class Refresh extends BaseEntity {
    @Column
    private String token;
    @Column
    private boolean used;
    @Column
    private LocalDateTime expireDate;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Refresh() {
    }

    public Refresh(String token, boolean used,LocalDateTime expireDate) {
        this.token = token;
        this.used = used;
        this.expireDate = expireDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDateTime expireDate) {
        this.expireDate = expireDate;
    }
}