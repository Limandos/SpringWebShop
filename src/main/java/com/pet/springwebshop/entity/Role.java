package com.pet.springwebshop.entity;

public enum Role {
    USER("USER"),
    ADMIN("ADMIN");

    private final String title;

    Role(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}