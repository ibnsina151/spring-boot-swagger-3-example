package com.aikoder.spring.swagger.model;

import javax.annotation.Nonnull;

public class Wheel {
    @Nonnull
    private String brand;
    private int size;

    public Wheel(@Nonnull String brand, int size) {
        this.brand = brand;
        this.size = size;
    }

    @Nonnull
    public String getBrand() {
        return brand;
    }

    public int getSize() {
        return size;
    }
}
