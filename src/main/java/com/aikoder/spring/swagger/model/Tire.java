package com.aikoder.spring.swagger.model;

import javax.annotation.Nonnull;

public class Tire {
    @Nonnull
    private String type;
    private int pressure;

    public Tire(@Nonnull String type, int pressure) {
        this.type = type;
        this.pressure = pressure;
    }

    @Nonnull
    public String getType() {
        return type;
    }

    public int getPressure() {
        return pressure;
    }
}
