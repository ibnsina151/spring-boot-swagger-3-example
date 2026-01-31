package com.aikoder.spring.swagger.model;

import javax.annotation.Nonnull;
import java.util.List;

public class Car {

    @Nonnull
    private String name;

    @Nonnull
    private Engine engine;

    @Nonnull
    private List<Wheel> wheels;

    @Nonnull
    private List<Tire> tires;

    public Car(@Nonnull String name, @Nonnull Engine engine,
               @Nonnull List<Wheel> wheels, @Nonnull List<Tire> tires) {
        this.name = name;
        this.engine = engine;
        this.wheels = wheels;
        this.tires = tires;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    @Nonnull
    public Engine getEngine() {
        return engine;
    }

    @Nonnull
    public List<Wheel> getWheels() {
        return wheels;
    }

    @Nonnull
    public List<Tire> getTires() {
        return tires;
    }
}
