package com.aikoder.spring.swagger.model;

import javax.annotation.Nonnull;

public class Engine {
    @Nonnull
    private String model;
    private int horsepower;

    public Engine(@Nonnull String model, int horsepower) {
        this.model = model;
        this.horsepower = horsepower;
    }

    @Nonnull
    public String getModel() {
        return model;
    }

    public int getHorsepower() {
        return horsepower;
    }
}
